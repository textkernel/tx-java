package com.sovren;

import com.sovren.exceptions.*;
import com.sovren.http.HttpResponse;
import com.sovren.models.GeoCoordinates;
import com.sovren.models.api.ApiResponse;
import com.sovren.models.api.ApiResponseInfoLite;
import com.sovren.models.api.account.GetAccountInfoResponse;
import com.sovren.models.api.bimetricscoring.*;
import com.sovren.models.api.geocoding.*;
import com.sovren.models.api.indexes.*;
import com.sovren.models.api.matching.*;
import com.sovren.models.api.matching.request.FilterCriteria;
import com.sovren.models.api.matching.request.MatchByDocumentIdOptions;
import com.sovren.models.api.matching.request.PaginationSettings;
import com.sovren.models.api.matching.request.SearchMatchSettings;
import com.sovren.models.api.matching.ui.GenerateUIResponse;
import com.sovren.models.api.matching.ui.request.*;
import com.sovren.models.api.matching.ui.UIOptions;
import com.sovren.models.job.ParsedJob;
import com.sovren.models.resume.ParsedResume;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


import com.sovren.models.api.parsing.ParseRequest;
import com.sovren.models.api.parsing.ParseResumeResponse;
import com.sovren.models.api.parsing.ParseJobResponse;
import com.sovren.models.matching.IndexType;
import com.sovren.utilities.SovrenJsonSerializer;

import java.io.IOException;
import java.time.Instant;
import java.util.List;


/**
 * The SDK client to perform Sovren API calls.
 */
public class SovrenClient {
    private GeocodeCredentials _geocodeCreds;
    private ApiEndpoints _endpoints;
    private OkHttpClient _client;
    private static final MediaType JSON = MediaType.get("application/json; charset=utf-8");
    private static final String _sdkVersion;
    
    static {
        //_sdkVersion = SovrenClient.class.getPackage().getSpecificationVersion();//TODO: fix this
        _sdkVersion = "0.1.0-beta";
    }
    
    /** 
     * Set to {@code true} for debugging API errors. It will show the full JSON request body in {@link SovrenException#RequestBody}
     * <br><b>NOTE: do not set this to {@code true} in your production system, as it increases the memory footprint</b>
     */
    public boolean ShowFullRequestBodyInExceptions = false;

    /**
     * Create an SDK client to perform Sovren API calls with the account information found at https://portal.sovren.com
     * @param accountId - The account id for your account
     * @param serviceKey - The service key for your account
     * @param dataCenter - The Data Center for your account. Either {@link DataCenter#US} or {@link DataCenter#EU}
     * @param geocodeCredentials - The credentials you want to use for geocoding, or {@code null}
     * 
     * @throws IllegalArgumentException if the accountId, serviceKey, or dataCenter are null/empty
     */
    public SovrenClient(String accountId, String serviceKey, DataCenter dataCenter, GeocodeCredentials geocodeCredentials) {
        
        if (accountId == null || accountId.length() == 0) {
            throw new IllegalArgumentException("'accountId' must have a valid value");
        }

        if (serviceKey == null || serviceKey.length() == 0) {
            throw new IllegalArgumentException("'serviceKey' must have a valid value");
        }

        if (dataCenter == null) {
            throw new IllegalArgumentException("'dataCenter' must not be null");
        }

        _endpoints = new ApiEndpoints(dataCenter);
        _geocodeCreds = geocodeCredentials;

        if (_geocodeCreds == null) {
            _geocodeCreds = new GeocodeCredentials();
            _geocodeCreds.Provider = GeocodeProvider.Google;
        }

        //do not validate credentials here, as this could lead to calling GetAccount for every parse call, an AUP violation
        _client = new OkHttpClient.Builder()
            .addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Interceptor.Chain chain) throws IOException {
                    Request original = chain.request();

                    //set all of these headers on every request
                    Request request = original.newBuilder()
                        .header("Sovren-AccountId", accountId)
                        .header("Sovren-ServiceKey", serviceKey)
                        .header("User-Agent", "sovren-java-" + _sdkVersion)
                        .build();

                    return chain.proceed(request);
                }
            })
            .build();
    }
    
    private static String serialize(Object o) {
        return SovrenJsonSerializer.serialize(o);
    }
    
    private <T extends ApiResponse> HttpResponse<T> executeRequest(Request apiRequest, Class<T> classOfT, String requestBody) throws SovrenException {
        
        ApiResponseInfoLite errorInfo = new ApiResponseInfoLite();
        errorInfo.Code = "Error";
        errorInfo.Message = "Unknown API error.";
        
        HttpResponse apiResponse = null;
        Response rawResponse = null;
        
        try {
            rawResponse = _client.newCall(apiRequest).execute();
            apiResponse = new HttpResponse(rawResponse, classOfT);
            
            if (apiResponse == null || apiResponse.getData() == null) throw new SovrenException(requestBody, rawResponse, errorInfo, null);
        }
        catch (IOException e) {
            errorInfo.Message = e.getMessage();
            SovrenException newEx = new SovrenException(requestBody, rawResponse, errorInfo, null);
            newEx.InnerException = e;
            throw newEx;
        }
       
        if (!rawResponse.isSuccessful()) throw new SovrenException(requestBody, rawResponse, apiResponse.getData().Info);
        
        return apiResponse;
    }
    
    private GenerateUIResponse executeUIRequest(Request apiRequest, String requestBody) throws SovrenException {

        ApiResponseInfoLite errorInfo = new ApiResponseInfoLite();
        errorInfo.Code = "Error";
        errorInfo.Message = "Unknown API error.";

        GenerateUIResponse apiResponse = null;
        Response rawResponse = null;
        String transId = "matchui-" + Instant.now().toString();

        try {
            rawResponse = _client.newCall(apiRequest).execute();

            if (!rawResponse.isSuccessful()) {
                errorInfo.Message = rawResponse.body().string();
                throw new SovrenException(requestBody, rawResponse, errorInfo, transId);
            }

            String responseBodyStr = rawResponse.body().string();
            apiResponse = SovrenJsonSerializer.deserialize(responseBodyStr, GenerateUIResponse.class);

            if (apiResponse == null) throw new SovrenException(requestBody, rawResponse, errorInfo, transId);
        }
        catch (IOException e) {
            errorInfo.Message = e.getMessage();
            SovrenException newEx = new SovrenException(requestBody, rawResponse, errorInfo, transId);
            newEx.InnerException = e;
            throw newEx;
        }

        return apiResponse;
    }
    
    private String getBodyIfDebug(Request request) {
        
        if (ShowFullRequestBodyInExceptions) {
            return request.body().toString();
        }
        
        return null;
    }
    
    /**
     * Get the account info (remaining credits, max concurrency, etc).
     * @return The API response body
     * @throws SovrenException Thrown when an API error occurs
     */
    public GetAccountInfoResponse getAccountInfo() throws SovrenException {
        Request apiRequest = new Request.Builder()
            .url(_endpoints.account())
            .build();

        HttpResponse<GetAccountInfoResponse> response = executeRequest(apiRequest, GetAccountInfoResponse.class, getBodyIfDebug(apiRequest));
        return response.getData();
    }
    
    /**
     * Parse a resume
     * @param request The request body
     * @return The API response body
     * @throws SovrenException Thrown when an API error occurs
     */
    public ParseResumeResponse parseResume(ParseRequest request) throws SovrenException {
        RequestBody body = RequestBody.create(serialize(request), JSON);
        Request apiRequest = new Request.Builder()
            .url(_endpoints.parseResume())
            .post(body)
            .build();

        HttpResponse<ParseResumeResponse> response = executeRequest(apiRequest, ParseResumeResponse.class, getBodyIfDebug(apiRequest));

        if (response.getData().Value.ParsingResponse != null && !response.getData().Value.ParsingResponse.isSuccess()) {
            throw new SovrenException(getBodyIfDebug(apiRequest), response.getResponse(), response.getData().Value.ParsingResponse, response.getData().Info.TransactionId);
        }

        if (response.getData().Value.GeocodeResponse != null && !response.getData().Value.GeocodeResponse.isSuccess()) {
            throw new SovrenGeocodeResumeException(response.getResponse(), response.getData().Value.GeocodeResponse, response.getData().Info.TransactionId, response.getData());
        }

        if (response.getData().Value.IndexingResponse != null && !response.getData().Value.IndexingResponse.isSuccess()) {
            throw new SovrenIndexResumeException(response.getResponse(), response.getData().Value.IndexingResponse, response.getData().Info.TransactionId, response.getData());
        }
        
        return response.getData();
    }
    
    /**
     * Parse a job
     * @param request The request body
     * @return The API response body
     * @throws SovrenException Thrown when an API error occurs
     */
    public ParseJobResponse parseJob(ParseRequest request) throws SovrenException {
        RequestBody body = RequestBody.create(serialize(request), JSON);
        Request apiRequest = new Request.Builder()
            .url(_endpoints.parseJob())
            .post(body)
            .build();

        HttpResponse<ParseJobResponse> response = executeRequest(apiRequest, ParseJobResponse.class, getBodyIfDebug(apiRequest));

        if (response.getData().Value.ParsingResponse != null && !response.getData().Value.ParsingResponse.isSuccess()) {
            throw new SovrenException(getBodyIfDebug(apiRequest), response.getResponse(), response.getData().Value.ParsingResponse, response.getData().Info.TransactionId);
        }

        if (response.getData().Value.GeocodeResponse != null && !response.getData().Value.GeocodeResponse.isSuccess()) {
            throw new SovrenGeocodeJobException(response.getResponse(), response.getData().Value.GeocodeResponse, response.getData().Info.TransactionId, response.getData());
        }

        if (response.getData().Value.IndexingResponse != null && !response.getData().Value.IndexingResponse.isSuccess()) {
            throw new SovrenIndexJobException(response.getResponse(), response.getData().Value.IndexingResponse, response.getData().Info.TransactionId, response.getData());
        }
        
        return response.getData();
    }
    
    /**
     * Create a new index
     * @param type The type of documents stored in this index. Either 'Resume' or 'Job'
     * @param indexId
     * The ID to assign to the new index. This is restricted to alphanumeric with dashes 
     * and underscores. All values will be converted to lower-case.
     * @return The API response body
     * @throws SovrenException Thrown when an API error occurs
     */
    public CreateIndexResponse createIndex(IndexType type, String indexId) throws SovrenException {
        CreateIndexRequest request = new CreateIndexRequest();
        request.IndexType = type;
        
        RequestBody body = RequestBody.create(serialize(request), JSON);
        Request apiRequest = new Request.Builder()
            .url(_endpoints.index(indexId))
            .post(body)
            .build();
        
        HttpResponse<CreateIndexResponse> response = executeRequest(apiRequest, CreateIndexResponse.class, getBodyIfDebug(apiRequest));
        return response.getData();
    }
    
    /**
     * Get all existing indexes
     * @return The API response body
     * @throws SovrenException Thrown when an API error occurs
     */
    public GetAllIndexesResponse getAllIndexes() throws SovrenException {
        Request apiRequest = new Request.Builder()
            .url(_endpoints.allIndexes())
            .build();
        
        HttpResponse<GetAllIndexesResponse> response = executeRequest(apiRequest, GetAllIndexesResponse.class, getBodyIfDebug(apiRequest));
        return response.getData();
    }
    
    /**
     * Delete an existing index. Note that this is a destructive action and cannot be undone. 
     * All the documents in this index will be deleted.
     * @param indexId The index to delete (case-insensitive).
     * @return The API response body
     * @throws SovrenException Thrown when an API error occurs
     */
    public DeleteIndexResponse deleteIndex(String indexId) throws SovrenException {
        Request apiRequest = new Request.Builder()
            .url(_endpoints.index(indexId))
            .delete()
            .build();
        
        HttpResponse<DeleteIndexResponse> response = executeRequest(apiRequest, DeleteIndexResponse.class, getBodyIfDebug(apiRequest));
        return response.getData();
    }

    /**
     * Add a resume to an existing index
     * @param resume A resume generated by the Sovren Resume Parser
     * @param indexId The index the document should be added into (case-insensitive).
     * @param documentId
     * The ID to assign to the new document. This is restricted to alphanumeric 
     * with dashes and underscores. All values will be converted to lower-case.
     * @param userDefinedTags The user-defined tags that the resume should have, or {@code null}
     * @return The API response body
     * @throws SovrenException Thrown when an API error occurs
     */
    public IndexDocumentResponse indexDocument(
            ParsedResume resume,
            String indexId,
            String documentId,
            List<String> userDefinedTags) throws SovrenException {
        IndexResumeRequest request = new IndexResumeRequest();
        request.ResumeData = resume;
        request.UserDefinedTags = userDefinedTags;

        RequestBody body = RequestBody.create(serialize(request), JSON);
        Request apiRequest = new Request.Builder()
                .url(_endpoints.resume(indexId, documentId))
                .post(body)
                .build();

        HttpResponse<IndexDocumentResponse> response = executeRequest(apiRequest, IndexDocumentResponse.class, getBodyIfDebug(apiRequest));
        return response.getData();
    }

    /**
     * Add a job to an existing index
     * @param job A job generated by the Sovren Job Parser
     * @param indexId The index the document should be added into (case-insensitive).
     * @param documentId
     * The ID to assign to the new document. This is restricted to alphanumeric
     * with dashes and underscores. All values will be converted to lower-case.
     * @param userDefinedTags The user-defined tags that the resume should have, or {@code null}
     * @return The API response body
     * @throws SovrenException Thrown when an API error occurs
     */
    public IndexDocumentResponse indexDocument(
            ParsedJob job,
            String indexId,
            String documentId,
            List<String> userDefinedTags) throws SovrenException {
        IndexJobRequest request = new IndexJobRequest();
        request.JobData = job;
        request.UserDefinedTags = userDefinedTags;

        RequestBody body = RequestBody.create(serialize(request), JSON);
        Request apiRequest = new Request.Builder()
                .url(_endpoints.job(indexId, documentId))
                .post(body)
                .build();

        HttpResponse<IndexDocumentResponse> response = executeRequest(apiRequest, IndexDocumentResponse.class, getBodyIfDebug(apiRequest));
        return response.getData();
    }

    /**
     * Add several resumes to an existing index
     * @param resumes The resumes generated by the Sovren Resume Parser paired with their DocumentIds
     * @param indexId The index the documents should be added into (case-insensitive).
     * @return The API response body
     * @throws SovrenException Thrown when an API error occurs
     */
    public IndexMultipleDocumentsResponse indexMultipleResumes(List<IndexResumeInfo> resumes, String indexId) throws SovrenException {
        IndexMultipleResumesRequest request = new IndexMultipleResumesRequest();
        request.Resumes = resumes;

        RequestBody body = RequestBody.create(serialize(request), JSON);
        Request apiRequest = new Request.Builder()
                .url(_endpoints.multipleResumes(indexId))
                .post(body)
                .build();

        HttpResponse<IndexMultipleDocumentsResponse> response = executeRequest(apiRequest, IndexMultipleDocumentsResponse.class, getBodyIfDebug(apiRequest));
        return response.getData();
    }

    /**
     * Add several jobs to an existing index
     * @param jobs The jobs generated by the Sovren Job Parser paired with their DocumentIds
     * @param indexId The index the documents should be added into (case-insensitive).
     * @return The API response body
     * @throws SovrenException Thrown when an API error occurs
     */
    public IndexMultipleDocumentsResponse indexMultipleJobs(List<IndexJobInfo> jobs, String indexId) throws SovrenException {
        IndexMultipleJobsRequest request = new IndexMultipleJobsRequest();
        request.Jobs = jobs;

        RequestBody body = RequestBody.create(serialize(request), JSON);
        Request apiRequest = new Request.Builder()
                .url(_endpoints.multipleJobs(indexId))
                .post(body)
                .build();

        HttpResponse<IndexMultipleDocumentsResponse> response = executeRequest(apiRequest, IndexMultipleDocumentsResponse.class, getBodyIfDebug(apiRequest));
        return response.getData();
    }

    /**
     * Delete an existing document from an index
     * @param indexId The index containing the document
     * @param documentId The ID of the document to delete
     * @return The API response body
     * @throws SovrenException Thrown when an API error occurs
     */
    public DeleteDocumentResponse deleteDocument(String indexId, String documentId) throws SovrenException {
        Request apiRequest = new Request.Builder()
                .url(_endpoints.document(indexId, documentId))
                .delete()
                .build();

        HttpResponse<DeleteDocumentResponse> response = executeRequest(apiRequest, DeleteDocumentResponse.class, getBodyIfDebug(apiRequest));
        return response.getData();
    }

    /**
     * Delete a group of existing documents from an index
     * @param indexId The index containing the documents
     * @param documentIds The IDs of the documents to delete
     * @return The API response body
     * @throws SovrenException Thrown when an API error occurs
     */
    public DeleteMultipleDocumentsResponse deleteMultipleDocuments(String indexId, List<String> documentIds) throws SovrenException {
        RequestBody requestBody = RequestBody.create(serialize(documentIds), JSON);
        Request apiRequest = new Request.Builder()
                .url(_endpoints.multipleDocuments(indexId))
                .delete(requestBody)
                .build();

        HttpResponse<DeleteMultipleDocumentsResponse> response = executeRequest(apiRequest, DeleteMultipleDocumentsResponse.class, getBodyIfDebug(apiRequest));
        return response.getData();
    }

    /**
     * Retrieve an existing resume from an index
     * @param indexId The index containing the resume
     * @param documentId The ID of the resume to retrieve
     * @return The API response body
     * @throws SovrenException Thrown when an API error occurs
     */
    public GetResumeResponse getResume(String indexId, String documentId) throws SovrenException {
        Request apiRequest = new Request.Builder()
                .url(_endpoints.resume(indexId, documentId))
                .build();

        HttpResponse<GetResumeResponse> response = executeRequest(apiRequest, GetResumeResponse.class, getBodyIfDebug(apiRequest));
        return response.getData();
    }

    /**
     * Retrieve an existing job from an index
     * @param indexId The index containing the job
     * @param documentId The ID of the job to retrieve
     * @return The API response body
     * @throws SovrenException Thrown when an API error occurs
     */
    public GetJobResponse getJob(String indexId, String documentId) throws SovrenException {
        Request apiRequest = new Request.Builder()
                .url(_endpoints.job(indexId, documentId))
                .build();

        HttpResponse<GetJobResponse> response = executeRequest(apiRequest, GetJobResponse.class, getBodyIfDebug(apiRequest));
        return response.getData();
    }

    /**
     * Updates the user-defined tags for a resume
     * @param indexId The index containing the resume
     * @param documentId The ID of the resume to update
     * @param userDefinedTags The user-defined tags to add/delete/etc
     * @param method Which method to use for the specified user-defined tags
     * @return The API response body
     * @throws SovrenException Thrown when an API error occurs
     */
    public UpdateUserDefinedTagsResponse updateResumeUserDefinedTags(
            String indexId,
            String documentId,
            List<String> userDefinedTags,
            UserDefinedTagsMethod method) throws SovrenException {
        UpdateUserDefinedTagsRequest request = new UpdateUserDefinedTagsRequest();
        request.UserDefinedTags = userDefinedTags;
        request.Method = method;

        RequestBody body = RequestBody.create(serialize(request), JSON);
        Request apiRequest = new Request.Builder()
                .url(_endpoints.resume(indexId, documentId))
                .patch(body)
                .build();

        HttpResponse<UpdateUserDefinedTagsResponse> response = executeRequest(apiRequest, UpdateUserDefinedTagsResponse.class, getBodyIfDebug(apiRequest));
        return response.getData();
    }

    /**
     * Updates the user-defined tags for a job
     * @param indexId The index containing the job
     * @param documentId The ID of the job to update
     * @param userDefinedTags The user-defined tags to add/delete/etc
     * @param method Which method to use for the specified user-defined tags
     * @return The API response body
     * @throws SovrenException Thrown when an API error occurs
     */
    public UpdateUserDefinedTagsResponse updateJobUserDefinedTags(
            String indexId,
            String documentId,
            List<String> userDefinedTags,
            UserDefinedTagsMethod method) throws SovrenException {
        UpdateUserDefinedTagsRequest request = new UpdateUserDefinedTagsRequest();
        request.UserDefinedTags = userDefinedTags;
        request.Method = method;

        RequestBody body = RequestBody.create(serialize(request), JSON);
        Request apiRequest = new Request.Builder()
                .url(_endpoints.job(indexId, documentId))
                .patch(body)
                .build();

        HttpResponse<UpdateUserDefinedTagsResponse> response = executeRequest(apiRequest, UpdateUserDefinedTagsResponse.class, getBodyIfDebug(apiRequest));
        return response.getData();
    }

    /**
     * Find matches for a non-indexed resume.
     * @param resume The resume (generated by the Sovren Resume Parser) to use as the source for a match query
     * @param indexesToQuery The indexes to find results in. These must all be of the same type (resumes or jobs)
     * @param preferredWeights
     * The preferred category weights for scoring the results. If {@code null},
     * Sovren will determine the best values based on the source resume
     * @param filters Any filters to apply prior to the match (a result must satisfy all the filters), or {@code null}
     * @param settings The settings for this match request. Use {@code null} for defaults.
     * @param numResults The number of results to show. Use {@code 0} for the default.
     * @return The API response body
     * @throws SovrenException Thrown when an API error occurs
     */
    public MatchResponse match(
            ParsedResume resume,
            List<String> indexesToQuery,
            CategoryWeights preferredWeights,
            FilterCriteria filters,
            SearchMatchSettings settings,
            int numResults) throws SovrenException {

        MatchResumeRequest request = createRequest(resume, indexesToQuery, preferredWeights, filters, settings, numResults);
        RequestBody body = RequestBody.create(serialize(request), JSON);
        Request apiRequest = new Request.Builder()
                .url(_endpoints.matchResume(false))
                .post(body)
                .build();

        HttpResponse<MatchResponse> response = executeRequest(apiRequest, MatchResponse.class, getBodyIfDebug(apiRequest));
        return response.getData();
    }

    MatchResumeRequest createRequest(
            ParsedResume resume,
            List<String> indexesToQuery,
            CategoryWeights preferredWeights,
            FilterCriteria filters,
            SearchMatchSettings settings,
            int numResults) {
        MatchResumeRequest request = new MatchResumeRequest();
        request.ResumeData = resume;
        request.IndexIdsToSearchInto = indexesToQuery;
        request.PreferredCategoryWeights = preferredWeights;
        request.FilterCriteria = filters;
        request.Settings = settings;
        request.Take = numResults;
        return request;
    }

    /**
     * Find matches for a non-indexed job.
     * @param job The job (generated by the Sovren Job Parser) to use as the source for a match query
     * @param indexesToQuery The indexes to find results in. These must all be of the same type (resumes or jobs)
     * @param preferredWeights
     * The preferred category weights for scoring the results. If {@code null},
     * Sovren will determine the best values based on the source job
     * @param filters Any filters to apply prior to the match (a result must satisfy all the filters), or {@code null}
     * @param settings The settings for this match request. Use {@code null} for defaults.
     * @param numResults The number of results to show. Use {@code 0} for the default.
     * @return The API response body
     * @throws SovrenException Thrown when an API error occurs
     */
    public MatchResponse match(
            ParsedJob job,
            List<String> indexesToQuery,
            CategoryWeights preferredWeights,
            FilterCriteria filters,
            SearchMatchSettings settings,
            int numResults) throws SovrenException {

        MatchJobRequest request = createRequest(job, indexesToQuery, preferredWeights, filters, settings, numResults);
        RequestBody body = RequestBody.create(serialize(request), JSON);
        Request apiRequest = new Request.Builder()
                .url(_endpoints.matchJob(false))
                .post(body)
                .build();

        HttpResponse<MatchResponse> response = executeRequest(apiRequest, MatchResponse.class, getBodyIfDebug(apiRequest));
        return response.getData();
    }

    MatchJobRequest createRequest(
            ParsedJob job,
            List<String> indexesToQuery,
            CategoryWeights preferredWeights,
            FilterCriteria filters,
            SearchMatchSettings settings,
            int numResults) {
        MatchJobRequest request = new MatchJobRequest();
        request.JobData = job;
        request.IndexIdsToSearchInto = indexesToQuery;
        request.PreferredCategoryWeights = preferredWeights;
        request.FilterCriteria = filters;
        request.Settings = settings;
        request.Take = numResults;
        return request;
    }

    /**
     * Find matches for a resume or job that is already indexed
     * @param indexId The index containing the document you want to match
     * @param documentId The ID of the document to match
     * @param indexesToQuery The indexes to find results in. These must all be of the same type (resumes or jobs)
     * @param preferredWeights
     * The preferred category weights for scoring the results. If {@code null},
     * Sovren will determine the best values based on the source resume/job
     * @param filters Any filters to apply prior to the match (a result must satisfy all the filters), or {@code null}
     * @param settings The settings for this match request. Use {@code null} for defaults.
     * @param numResults The number of results to show. Use {@code 0} for the default.
     * @return The API response body
     * @throws SovrenException Thrown when an API error occurs
     */
    public MatchResponse match(
            String indexId,
            String documentId,
            List<String> indexesToQuery,
            CategoryWeights preferredWeights,
            FilterCriteria filters,
            SearchMatchSettings settings,
            int numResults) throws SovrenException {

        MatchByDocumentIdOptions request = createRequest(indexesToQuery, preferredWeights, filters, settings, numResults);
        RequestBody body = RequestBody.create(serialize(request), JSON);
        Request apiRequest = new Request.Builder()
                .url(_endpoints.matchDocId(indexId, documentId, false))
                .post(body)
                .build();

        HttpResponse<MatchResponse> response = executeRequest(apiRequest, MatchResponse.class, getBodyIfDebug(apiRequest));
        return response.getData();
    }

    MatchByDocumentIdOptions createRequest(
            List<String> indexesToQuery,
            CategoryWeights preferredWeights,
            FilterCriteria filters,
            SearchMatchSettings settings,
            int numResults) {
        MatchByDocumentIdOptions request = new MatchByDocumentIdOptions();
        request.IndexIdsToSearchInto = indexesToQuery;
        request.PreferredCategoryWeights = preferredWeights;
        request.FilterCriteria = filters;
        request.Settings = settings;
        request.Take = numResults;
        return request;
    }

    GenerateUIResponse uiMatch(String indexId, String documentId, UIMatchByDocumentIdOptions options) throws SovrenException {
        RequestBody body = RequestBody.create(serialize(options), JSON);
        Request apiRequest = new Request.Builder()
                .url(_endpoints.matchDocId(indexId, documentId, true))
                .post(body)
                .build();

        GenerateUIResponse response = executeUIRequest(apiRequest, getBodyIfDebug(apiRequest));
        return response;
    }

    GenerateUIResponse uiMatch(UIMatchResumeRequest request) throws SovrenException {
        RequestBody body = RequestBody.create(serialize(request), JSON);
        Request apiRequest = new Request.Builder()
                .url(_endpoints.matchResume(true))
                .post(body)
                .build();

        GenerateUIResponse response = executeUIRequest(apiRequest, getBodyIfDebug(apiRequest));
        return response;
    }

    GenerateUIResponse uiMatch(UIMatchJobRequest request) throws SovrenException {
        RequestBody body = RequestBody.create(serialize(request), JSON);
        Request apiRequest = new Request.Builder()
                .url(_endpoints.matchJob(true))
                .post(body)
                .build();

        GenerateUIResponse response = executeUIRequest(apiRequest, getBodyIfDebug(apiRequest));
        return response;
    }


    /**
     * Search for resumes or jobs that meet specific criteria
     * @param indexesToQuery The indexes to find results in. These must all be of the same type (resumes or jobs)
     * @param query The search query. A result must satisfy all of these criteria
     * @param settings The settings for this search request. Use {@code null} for defaults.
     * @param pagination Pagination settings. Use {@code null} for defaults.
     * @return The API response body
     * @throws SovrenException Thrown when an API error occurs
     */
    public SearchResponse search(
            List<String> indexesToQuery,
            FilterCriteria query,
            SearchMatchSettings settings,
            PaginationSettings pagination) throws SovrenException {
        SearchRequest request = createRequest(indexesToQuery, query, settings, pagination);
        RequestBody body = RequestBody.create(serialize(request), JSON);
        Request apiRequest = new Request.Builder()
                .url(_endpoints.search(false))
                .post(body)
                .build();

        HttpResponse<SearchResponse> response = executeRequest(apiRequest, SearchResponse.class, getBodyIfDebug(apiRequest));
        return response.getData();
    }

    SearchRequest createRequest(List<String> indexesToQuery, FilterCriteria query, SearchMatchSettings settings, PaginationSettings pagination) {
        SearchRequest request = new SearchRequest();
        request.IndexIdsToSearchInto = indexesToQuery;
        request.FilterCriteria = query;
        request.Settings = settings;
        request.PaginationSettings = pagination;
        return request;
    }

    GenerateUIResponse uiSearch(UISearchRequest request) throws SovrenException {
        RequestBody body = RequestBody.create(serialize(request), JSON);
        Request apiRequest = new Request.Builder()
                .url(_endpoints.search(true))
                .post(body)
                .build();

        GenerateUIResponse response = executeUIRequest(apiRequest, getBodyIfDebug(apiRequest));
        return response;
    }


    /**
     * Score one or more target documents against a source resume
     * @param <TTarget> Either {@link ParsedResumeWithId} or {@link ParsedJobWithId}
     * @param sourceResume The source resume
     * @param targetDocuments The target resumes/jobs
     * @param preferredWeights
     * The preferred category weights for scoring the results. If {@code null},
     * Sovren will determine the best values based on the source resume.
     * @param settings The settings for this search request. Use {@code null} for defaults.
     * @return The API response body
     * @throws SovrenException Thrown when an API error occurs
     */
    public <TTarget extends IParsedDocWithId> BimetricScoreResponse bimetricScore(
            ParsedResumeWithId sourceResume,
            List<TTarget> targetDocuments,
            CategoryWeights preferredWeights,
            SearchMatchSettings settings) throws SovrenException {
        BimetricScoreResumeRequest request = createRequest(sourceResume, targetDocuments, preferredWeights, settings);
        RequestBody body = RequestBody.create(serialize(request), JSON);
        Request apiRequest = new Request.Builder()
                .url(_endpoints.bimetricScoreResume(false))
                .post(body)
                .build();

        HttpResponse<BimetricScoreResponse> response = executeRequest(apiRequest, BimetricScoreResponse.class, getBodyIfDebug(apiRequest));
        return response.getData();
    }

    <TTarget extends IParsedDocWithId> BimetricScoreResumeRequest createRequest(
            ParsedResumeWithId sourceResume,
            List<TTarget> targetDocuments,
            CategoryWeights preferredWeights,
            SearchMatchSettings settings) {
        BimetricScoreResumeRequest request = new BimetricScoreResumeRequest();
        request.PreferredCategoryWeights = preferredWeights;
        request.Settings = settings;
        request.SourceResume = sourceResume;

        if (targetDocuments.size() > 0) {
            //we must only cast/set either TargetResumes or TargetJobs here since
            // Java has type erasure and both casts would succeed and we'd send both params to the API
            if (targetDocuments.get(0) instanceof ParsedResumeWithId) {
                request.TargetResumes = (List<ParsedResumeWithId>)targetDocuments;
            }
            else {
                request.TargetJobs = (List<ParsedJobWithId>)targetDocuments;
            }
        }

        return request;
    }

    /**
     * Score one or more target documents against a source job
     * @param <TTarget> Either {@link ParsedResumeWithId} or {@link ParsedJobWithId}
     * @param sourceJob The source job
     * @param targetDocuments The target resumes/jobs
     * @param preferredWeights
     * The preferred category weights for scoring the results. If {@code null},
     * Sovren will determine the best values based on the source job.
     * @param settings The settings for this search request. Use {@code null} for defaults.
     * @return The API response body
     * @throws SovrenException Thrown when an API error occurs
     */
    public <TTarget extends IParsedDocWithId> BimetricScoreResponse bimetricScore(
            ParsedJobWithId sourceJob,
            List<TTarget> targetDocuments,
            CategoryWeights preferredWeights,
            SearchMatchSettings settings) throws SovrenException {
        BimetricScoreJobRequest request = createRequest(sourceJob, targetDocuments, preferredWeights, settings);
        RequestBody body = RequestBody.create(serialize(request), JSON);
        Request apiRequest = new Request.Builder()
                .url(_endpoints.bimetricScoreJob(false))
                .post(body)
                .build();

        HttpResponse<BimetricScoreResponse> response = executeRequest(apiRequest, BimetricScoreResponse.class, getBodyIfDebug(apiRequest));
        return response.getData();
    }

    <TTarget extends IParsedDocWithId> BimetricScoreJobRequest createRequest(
            ParsedJobWithId sourceJob,
            List<TTarget> targetDocuments,
            CategoryWeights preferredWeights,
            SearchMatchSettings settings) {
        BimetricScoreJobRequest request = new BimetricScoreJobRequest();
        request.PreferredCategoryWeights = preferredWeights;
        request.Settings = settings;
        request.SourceJob = sourceJob;

        if (targetDocuments.size() > 0) {
            //we must only cast/set either TargetResumes or TargetJobs here since
            // Java has type erasure and both casts would succeed and we'd send both params to the API
            if (targetDocuments.get(0) instanceof ParsedResumeWithId) {
                request.TargetResumes = (List<ParsedResumeWithId>)targetDocuments;
            }
            else {
                request.TargetJobs = (List<ParsedJobWithId>)targetDocuments;
            }
        }

        return request;
    }

    GenerateUIResponse uiBimetricScore(UIBimetricScoreResumeRequest request) throws SovrenException {
        RequestBody body = RequestBody.create(serialize(request), JSON);
        Request apiRequest = new Request.Builder()
                .url(_endpoints.bimetricScoreResume(true))
                .post(body)
                .build();

        GenerateUIResponse response = executeUIRequest(apiRequest, getBodyIfDebug(apiRequest));
        return response;
    }

    GenerateUIResponse uiBimetricScore(UIBimetricScoreJobRequest request) throws SovrenException {
        RequestBody body = RequestBody.create(serialize(request), JSON);
        Request apiRequest = new Request.Builder()
                .url(_endpoints.bimetricScoreJob(true))
                .post(body)
                .build();

        GenerateUIResponse response = executeUIRequest(apiRequest, getBodyIfDebug(apiRequest));
        return response;
    }

    private GeocodeResumeResponse internalGeocode(ParsedResume resume, Address address) throws SovrenException {
        GeocodeResumeRequest request = new GeocodeResumeRequest();
        request.ResumeData = resume;
        request.Provider = _geocodeCreds.Provider;
        request.ProviderKey = _geocodeCreds.ProviderKey;
        request.PostalAddress = address;

        RequestBody body = RequestBody.create(serialize(request), JSON);
        Request apiRequest = new Request.Builder()
                .url(_endpoints.geocodeResume())
                .post(body)
                .build();

        HttpResponse<GeocodeResumeResponse> response = executeRequest(apiRequest, GeocodeResumeResponse.class, getBodyIfDebug(apiRequest));
        return response.getData();
    }

    private GeocodeJobResponse internalGeocode(ParsedJob job, Address address) throws SovrenException {
        GeocodeJobRequest request = new GeocodeJobRequest();
        request.JobData = job;
        request.Provider = _geocodeCreds.Provider;
        request.ProviderKey = _geocodeCreds.ProviderKey;
        request.PostalAddress = address;

        RequestBody body = RequestBody.create(serialize(request), JSON);
        Request apiRequest = new Request.Builder()
                .url(_endpoints.geocodeJob())
                .post(body)
                .build();

        HttpResponse<GeocodeJobResponse> response = executeRequest(apiRequest, GeocodeJobResponse.class, getBodyIfDebug(apiRequest));
        return response.getData();
    }

    /**
     * Uses the address in the resume (if present) to look up geocoordinates and add them into the ParsedResume object.
     * These coordinates are used by the AI Searching/Matching engine.
     * @param resume The resume to insert the geocoordinates (from the address) into
     * @return The API response body
     * @throws SovrenException Thrown when an API error occurred
     */
    public GeocodeResumeResponse geocode(ParsedResume resume) throws SovrenException {
        return internalGeocode(resume, null);
    }

    /**
     * Use this if you would like to provide an address for geocoding instead of using the one in the parsed
     * resume. The address included in the parsed resume (if present) will not be modified.
     * @param resume The resume to insert the geocoordinates (from the address) into
     * @param address The address to use to retrieve geocoordinates
     * @return The API response body
     * @throws SovrenException Thrown when an API error occurred
     */
    public GeocodeResumeResponse geocode(ParsedResume resume, Address address) throws SovrenException {
        return internalGeocode(resume, address);
    }

    /**
     * Uses the address in the job (if present) to look up geocoordinates and add them into the ParsedJob object.
     * These coordinates are used by the AI Searching/Matching engine.
     * @param job The job to insert the geocoordinates (from the address) into
     * @return The API response body
     * @throws SovrenException Thrown when an API error occurred
     */
    public GeocodeJobResponse geocode(ParsedJob job) throws SovrenException {
        return internalGeocode(job, null);
    }

    /**
     * Use this if you would like to provide an address for geocoding instead of using the one in the parsed
     * job. The address included in the parsed job (if present) will not be modified.
     * @param job The job to insert the geocoordinates (from the address) into
     * @param address The address to use to retrieve geocoordinates
     * @return The API response body
     * @throws SovrenException Thrown when an API error occurred
     */
    public GeocodeJobResponse geocode(ParsedJob job, Address address) throws SovrenException {
        return internalGeocode(job, address);
    }

    private GeocodeAndIndexResumeResponse internalGeocodeAndIndex(
            ParsedResume resume,
            IndexSingleDocumentInfo indexingOptions,
            boolean indexIfGeocodeFails,
            Address address,
            GeoCoordinates coordinates) throws SovrenException {
        GeocodeOptionsBase options = new GeocodeOptionsBase();
        options.Provider = _geocodeCreds.Provider;
        options.ProviderKey = _geocodeCreds.ProviderKey;
        options.PostalAddress = address;
        options.GeoCoordinates = coordinates;

        GeocodeAndIndexResumeRequest request = new GeocodeAndIndexResumeRequest();
        request.ResumeData = resume;
        request.GeocodeOptions = options;
        request.IndexingOptions = indexingOptions;
        request.IndexIfGeocodeFails = indexIfGeocodeFails;

        RequestBody body = RequestBody.create(serialize(request), JSON);
        Request apiRequest = new Request.Builder()
                .url(_endpoints.geocodeAndIndexResume())
                .post(body)
                .build();

        HttpResponse<GeocodeAndIndexResumeResponse> response = executeRequest(apiRequest, GeocodeAndIndexResumeResponse.class, getBodyIfDebug(apiRequest));
        GeocodeAndIndexResumeResponseValue responseVal = response.getData().Value;

        if (!indexIfGeocodeFails && responseVal.GeocodeResponse != null && !responseVal.GeocodeResponse.isSuccess()) {
            throw new SovrenException(getBodyIfDebug(apiRequest), response.getResponse(), responseVal.GeocodeResponse, response.getData().getInfo().TransactionId);
        }

        if (responseVal.IndexingResponse != null && !responseVal.IndexingResponse.isSuccess()) {
            throw new SovrenException(getBodyIfDebug(apiRequest), response.getResponse(), responseVal.IndexingResponse, response.getData().getInfo().TransactionId);
        }

        return response.getData();
    }

    private GeocodeAndIndexJobResponse internalGeocodeAndIndex(
            ParsedJob job,
            IndexSingleDocumentInfo indexingOptions,
            boolean indexIfGeocodeFails,
            Address address,
            GeoCoordinates coordinates) throws SovrenException {
        GeocodeOptionsBase options = new GeocodeOptionsBase();
        options.Provider = _geocodeCreds.Provider;
        options.ProviderKey = _geocodeCreds.ProviderKey;
        options.PostalAddress = address;
        options.GeoCoordinates = coordinates;

        GeocodeAndIndexJobRequest request = new GeocodeAndIndexJobRequest();
        request.JobData = job;
        request.GeocodeOptions = options;
        request.IndexingOptions = indexingOptions;
        request.IndexIfGeocodeFails = indexIfGeocodeFails;

        RequestBody body = RequestBody.create(serialize(request), JSON);
        Request apiRequest = new Request.Builder()
                .url(_endpoints.geocodeAndIndexJob())
                .post(body)
                .build();

        HttpResponse<GeocodeAndIndexJobResponse> response = executeRequest(apiRequest, GeocodeAndIndexJobResponse.class, getBodyIfDebug(apiRequest));
        GeocodeAndIndexJobResponseValue responseVal = response.getData().Value;

        if (!indexIfGeocodeFails && responseVal.GeocodeResponse != null && !responseVal.GeocodeResponse.isSuccess()) {
            throw new SovrenException(getBodyIfDebug(apiRequest), response.getResponse(), responseVal.GeocodeResponse, response.getData().getInfo().TransactionId);
        }

        if (responseVal.IndexingResponse != null && !responseVal.IndexingResponse.isSuccess()) {
            throw new SovrenException(getBodyIfDebug(apiRequest), response.getResponse(), responseVal.IndexingResponse, response.getData().getInfo().TransactionId);
        }

        return response.getData();
    }

    /**
     * Uses the address in the resume (if present) to look up geocoordinates and add them into the ParsedResume object.
     * These coordinates are used by the AI Searching/Matching engine.
     * @param resume The resume to geocode
     * @param indexingOptions What index/document id to use to index the document after geocoding
     * @param indexIfGeocodeFails Indicates whether or not the document should still be added to the index if the geocode request fails.
     * @return The API response body
     * @throws SovrenException Thrown when an API error occurred
     */
    public GeocodeAndIndexResumeResponse geocodeAndIndex(
            ParsedResume resume,
            IndexSingleDocumentInfo indexingOptions,
            boolean indexIfGeocodeFails) throws SovrenException {
        return internalGeocodeAndIndex(resume, indexingOptions, indexIfGeocodeFails, null, null);
    }

    /**
     * Use this if you would like to provide an address for geocoding instead of using the one in the parsed
     * resume. The address included in the parsed resume (if present) will not be modified.
     * @param resume The resume to insert the geocoordinates (from the address) into
     * @param indexingOptions What index/document id to use to index the document after geocoding
     * @param address The address to use to retrieve geocoordinates
     * @param indexIfGeocodeFails Indicates whether or not the document should still be added to the index if the geocode request fails.
     * @return The API response body
     * @throws SovrenException Thrown when an API error occurred
     */
    public GeocodeAndIndexResumeResponse geocodeAndIndex(
            ParsedResume resume,
            IndexSingleDocumentInfo indexingOptions,
            Address address,
            boolean indexIfGeocodeFails) throws SovrenException {
        return internalGeocodeAndIndex(resume, indexingOptions, indexIfGeocodeFails, address, null);
    }

    /**
     * Use this if you already have latitude/longitude coordinates and simply wish to add them to your parsed resume.
     * The coordinates will be inserted into your parsed resume, and the address included in the
     * parsed resume (if present) will not be modified.
     * @param resume The resume to insert the geocoordinates into
     * @param indexingOptions What index/document id to use to index the document after geocoding
     * @param coordinates The geocoordinates to use
     * @param indexIfGeocodeFails Indicates whether or not the document should still be added to the index if the geocode request fails.
     * @return The API response body
     * @throws SovrenException Thrown when an API error occurred
     */
    public GeocodeAndIndexResumeResponse geocodeAndIndex(
            ParsedResume resume,
            IndexSingleDocumentInfo indexingOptions,
            GeoCoordinates coordinates,
            boolean indexIfGeocodeFails) throws SovrenException {
        return internalGeocodeAndIndex(resume, indexingOptions, indexIfGeocodeFails, null, coordinates);
    }

    /**
     * Uses the address in the job (if present) to look up geocoordinates and add them into the ParsedJob object.
     * These coordinates are used by the AI Searching/Matching engine.
     * @param job The job to geocode
     * @param indexingOptions What index/document id to use to index the document after geocoding
     * @param indexIfGeocodeFails Indicates whether or not the document should still be added to the index if the geocode request fails.
     * @return The API response body
     * @throws SovrenException Thrown when an API error occurred
     */
    public GeocodeAndIndexJobResponse geocodeAndIndex(
            ParsedJob job,
            IndexSingleDocumentInfo indexingOptions,
            boolean indexIfGeocodeFails) throws SovrenException {
        return internalGeocodeAndIndex(job, indexingOptions, indexIfGeocodeFails, null, null);
    }

    /**
     * Use this if you would like to provide an address for geocoding instead of using the one in the parsed
     * rjobesume. The address included in the parsed job (if present) will not be modified.
     * @param job The job to insert the geocoordinates (from the address) into
     * @param indexingOptions What index/document id to use to index the document after geocoding
     * @param address The address to use to retrieve geocoordinates
     * @param indexIfGeocodeFails Indicates whether or not the document should still be added to the index if the geocode request fails.
     * @return The API response body
     * @throws SovrenException Thrown when an API error occurred
     */
    public GeocodeAndIndexJobResponse geocodeAndIndex(
            ParsedJob job,
            IndexSingleDocumentInfo indexingOptions,
            Address address,
            boolean indexIfGeocodeFails) throws SovrenException {
        return internalGeocodeAndIndex(job, indexingOptions, indexIfGeocodeFails, address, null);
    }

    /**
     * Use this if you already have latitude/longitude coordinates and simply wish to add them to your parsed job.
     * The coordinates will be inserted into your parsed job, and the address included in the
     * parsed job (if present) will not be modified.
     * @param job The job to insert the geocoordinates into
     * @param indexingOptions What index/document id to use to index the document after geocoding
     * @param coordinates The geocoordinates to use
     * @param indexIfGeocodeFails Indicates whether or not the document should still be added to the index if the geocode request fails.
     * @return The API response body
     * @throws SovrenException Thrown when an API error occurred
     */
    public GeocodeAndIndexJobResponse geocodeAndIndex(
            ParsedJob job,
            IndexSingleDocumentInfo indexingOptions,
            GeoCoordinates coordinates,
            boolean indexIfGeocodeFails) throws SovrenException {
        return internalGeocodeAndIndex(job, indexingOptions, indexIfGeocodeFails, null, coordinates);
    }

    /**
     * Access methods for generating Sovren Matching UI sessions. For example: {@code sovClient.UI(options).search(...)}
     * @param uiOptions
     * Options/settings for the Matching UI.
     * <br>NOTE: if you do not provide a {@link UIOptions#Username} (in {@link MatchUISettings#UIOptions}),
     * the user will be prompted to login as soon as the Matching UI session is loaded
     * @return The client for making Matching UI API calls
     */
    public SovrenUIClient ui(MatchUISettings uiOptions) {
        return new SovrenUIClient(uiOptions, this);
    }
}