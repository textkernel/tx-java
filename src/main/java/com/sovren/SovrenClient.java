// Copyright © 2020 Sovren Group, Inc. All rights reserved.
// This file is provided for use by, or on behalf of, Sovren licensees
// within the terms of their license of Sovren products or Sovren customers
// within the Terms of Service pertaining to the Sovren SaaS products.

package com.sovren;

import com.sovren.exceptions.*;
import com.sovren.http.HttpResponse;
import com.sovren.models.GeoCoordinates;
import com.sovren.models.api.ApiResponse;
import com.sovren.models.api.ApiResponseInfoLite;
import com.sovren.models.api.account.GetAccountInfoResponse;
import com.sovren.models.api.bimetricscoring.*;
import com.sovren.models.api.dataenrichmentservices.ontology.request.CompareSkillsRequest;
import com.sovren.models.api.dataenrichmentservices.ontology.request.CompareSkillsToProfessionsRequest;
import com.sovren.models.api.dataenrichmentservices.ontology.request.SuggestProfessionsRequest;
import com.sovren.models.api.dataenrichmentservices.ontology.request.SuggestSkillsRequest;
import com.sovren.models.api.dataenrichmentservices.ontology.response.CompareSkillsResponse;
import com.sovren.models.api.dataenrichmentservices.ontology.response.CompareSkillsToProfessionsResponse;
import com.sovren.models.api.dataenrichmentservices.ontology.response.SuggestProfessionsResponse;
import com.sovren.models.api.dataenrichmentservices.ontology.response.SuggestSkillsResponse;
import com.sovren.models.api.dataenrichmentservices.professions.request.ProfessionsAutoCompleteRequest;
import com.sovren.models.api.dataenrichmentservices.professions.request.ProfessionsLookupRequest;
import com.sovren.models.api.dataenrichmentservices.professions.request.ProfessionsNormalizeRequest;
import com.sovren.models.api.dataenrichmentservices.professions.response.GetProfessionsTaxonomyResponse;
import com.sovren.models.api.dataenrichmentservices.professions.response.ProfessionsAutoCompleteResponse;
import com.sovren.models.api.dataenrichmentservices.professions.response.ProfessionsLookupResponse;
import com.sovren.models.api.dataenrichmentservices.professions.response.ProfessionsNormalizeResponse;
import com.sovren.models.api.dataenrichmentservices.skills.request.SkillsAutoCompleteRequest;
import com.sovren.models.api.dataenrichmentservices.skills.request.SkillsExtractRequest;
import com.sovren.models.api.dataenrichmentservices.skills.request.SkillsLookupRequest;
import com.sovren.models.api.dataenrichmentservices.skills.request.SkillsNormalizeRequest;
import com.sovren.models.api.dataenrichmentservices.skills.response.GetSkillsTaxonomyResponse;
import com.sovren.models.api.dataenrichmentservices.skills.response.SkillsAutoCompleteResponse;
import com.sovren.models.api.dataenrichmentservices.skills.response.SkillsExtractResponse;
import com.sovren.models.api.dataenrichmentservices.skills.response.SkillsLookupResponse;
import com.sovren.models.api.dataenrichmentservices.skills.response.SkillsNormalizeResponse;
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
    private ApiEndpoints _endpoints;
    private OkHttpClient _client;
    private static final MediaType JSON = MediaType.get("application/json; charset=utf-8");
    private static final String _sdkVersion;
    
    static {
        _sdkVersion = SovrenClient.class.getPackage().getImplementationVersion();
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
     * @param dataCenter - The Data Center for your account. Either {@link DataCenter#US}, {@link DataCenter#EU}, or @link DataCenter#AU}
     * @throws IllegalArgumentException if the accountId, serviceKey, or dataCenter are null/empty
     */
    public SovrenClient(String accountId, String serviceKey, DataCenter dataCenter) {
        this(accountId, serviceKey, dataCenter, null);
    }

    /**
     * Create an SDK client to perform Sovren API calls with the account information found at https://portal.sovren.com
     * @param accountId - The account id for your account
     * @param serviceKey - The service key for your account
     * @param dataCenter - The Data Center for your account. Either {@link DataCenter#US}, {@link DataCenter#EU} or {@link DataCenter#AU}
     * @param trackingTags - Optional tags to use to track API usage for your account
     * @throws IllegalArgumentException if the accountId, serviceKey, or dataCenter are null/empty
     */
    public SovrenClient(String accountId, String serviceKey, DataCenter dataCenter, List<String> trackingTags) {
        
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

        final String trackingTagsHeaderValue;//must be final to be passed into the interceptor below

        if (trackingTags != null && trackingTags.size() > 0) {
            trackingTagsHeaderValue = String.join(", ", trackingTags);
            if (trackingTagsHeaderValue.length() >= 75) {//API allows 100, but just to be safe, this should be way more than enough
                throw new IllegalArgumentException("'trackingTags' has too many values or the values are too long");
            }
        }
        else {
            trackingTagsHeaderValue = null;
        }

        //do not validate credentials here, as this could lead to calling GetAccount for every parse call, an AUP violation
        _client = new OkHttpClient.Builder()
            .addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Interceptor.Chain chain) throws IOException {
                    Request original = chain.request();

                    //set all of these headers on every request
                    okhttp3.Request.Builder builder = original.newBuilder();
                    builder.header("Sovren-AccountId", accountId);
                    builder.header("Sovren-ServiceKey", serviceKey);
                    builder.header("User-Agent", "sovren-java-" + _sdkVersion);

                    if (trackingTagsHeaderValue != null && !trackingTagsHeaderValue.isEmpty()){
                        builder.header("Sovren-TrackingTag", trackingTagsHeaderValue);
                    }

                    Request request = builder.build();
                    return chain.proceed(request);
                }
            })
            .build();
    }

    @SuppressWarnings("deprecation")
    private RequestBody createJsonBody(Object body) {
        // Use OkHttp v3 signature to ensure binary compatibility between v3 and v4
        // https://github.com/sovren/sovren-java/issues/36
        return RequestBody.create(JSON, SovrenJsonSerializer.serialize(body));
    }
    
    private <T extends ApiResponse<?>> HttpResponse<T> executeRequest(Request apiRequest, Class<T> classOfT, String requestBody) throws SovrenException {
        
        ApiResponseInfoLite errorInfo = new ApiResponseInfoLite();
        errorInfo.Code = "Error";
        errorInfo.Message = "Unknown API error.";
        
        HttpResponse<T> apiResponse = null;
        Response rawResponse = null;
        
        try {
            rawResponse = _client.newCall(apiRequest).execute();
            apiResponse = new HttpResponse<T>(rawResponse, classOfT);

            if (rawResponse != null && rawResponse.code() == 413) {
                errorInfo.Message = "Request body was too large.";
                throw new SovrenException(requestBody, rawResponse, errorInfo, null);
            }
            
            if (rawResponse != null && apiResponse.getData() == null && rawResponse.code() != 200) {
                //something went wrong, a non-200 status code
                errorInfo.Message = rawResponse.code() + " - " + rawResponse.message();
            }

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
            try {
                final Request copy = request.newBuilder().build();
                final okio.Buffer buffer = new okio.Buffer();
                copy.body().writeTo(buffer);
                return buffer.readUtf8();
            }
            catch (IOException e) {
                return null;
            }
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
        RequestBody body = createJsonBody(request);
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
        RequestBody body = createJsonBody(request);
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
        
        RequestBody body = createJsonBody(request);
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

        RequestBody body = createJsonBody(request);
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

        RequestBody body = createJsonBody(request);
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

        RequestBody body = createJsonBody(request);
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

        RequestBody body = createJsonBody(request);
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
        RequestBody requestBody = createJsonBody(documentIds);
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

        RequestBody body = createJsonBody(request);
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

        RequestBody body = createJsonBody(request);
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
        RequestBody body = createJsonBody(request);
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
        RequestBody body = createJsonBody(request);
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
        RequestBody body = createJsonBody(request);
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
        RequestBody body = createJsonBody(options);
        Request apiRequest = new Request.Builder()
                .url(_endpoints.matchDocId(indexId, documentId, true))
                .post(body)
                .build();

        GenerateUIResponse response = executeUIRequest(apiRequest, getBodyIfDebug(apiRequest));
        return response;
    }

    GenerateUIResponse uiMatch(UIMatchResumeRequest request) throws SovrenException {
        RequestBody body = createJsonBody(request);
        Request apiRequest = new Request.Builder()
                .url(_endpoints.matchResume(true))
                .post(body)
                .build();

        GenerateUIResponse response = executeUIRequest(apiRequest, getBodyIfDebug(apiRequest));
        return response;
    }

    GenerateUIResponse uiMatch(UIMatchJobRequest request) throws SovrenException {
        RequestBody body = createJsonBody(request);
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
        RequestBody body = createJsonBody(request);
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
        RequestBody body = createJsonBody(request);
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
        RequestBody body = createJsonBody(request);
        Request apiRequest = new Request.Builder()
                .url(_endpoints.bimetricScoreResume(false))
                .post(body)
                .build();

        HttpResponse<BimetricScoreResponse> response = executeRequest(apiRequest, BimetricScoreResponse.class, getBodyIfDebug(apiRequest));
        return response.getData();
    }

    @SuppressWarnings("unchecked") //these actually are checked, compiler just can't tell
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
        RequestBody body = createJsonBody(request);
        Request apiRequest = new Request.Builder()
                .url(_endpoints.bimetricScoreJob(false))
                .post(body)
                .build();

        HttpResponse<BimetricScoreResponse> response = executeRequest(apiRequest, BimetricScoreResponse.class, getBodyIfDebug(apiRequest));
        return response.getData();
    }

    @SuppressWarnings("unchecked") //these actually are checked, compiler just can't tell
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
        RequestBody body = createJsonBody(request);
        Request apiRequest = new Request.Builder()
                .url(_endpoints.bimetricScoreResume(true))
                .post(body)
                .build();

        GenerateUIResponse response = executeUIRequest(apiRequest, getBodyIfDebug(apiRequest));
        return response;
    }

    GenerateUIResponse uiBimetricScore(UIBimetricScoreJobRequest request) throws SovrenException {
        RequestBody body = createJsonBody(request);
        Request apiRequest = new Request.Builder()
                .url(_endpoints.bimetricScoreJob(true))
                .post(body)
                .build();

        GenerateUIResponse response = executeUIRequest(apiRequest, getBodyIfDebug(apiRequest));
        return response;
    }

    private GeocodeResumeResponse internalGeocode(ParsedResume resume, GeocodeCredentials geocodeCredentials, Address address) throws SovrenException {
        GeocodeResumeRequest request = new GeocodeResumeRequest();
        request.ResumeData = resume;
        request.Provider = geocodeCredentials != null ? geocodeCredentials.Provider : GeocodeProvider.Google;
        request.ProviderKey = geocodeCredentials != null ? geocodeCredentials.ProviderKey : null;
        request.PostalAddress = address;

        RequestBody body = createJsonBody(request);
        Request apiRequest = new Request.Builder()
                .url(_endpoints.geocodeResume())
                .post(body)
                .build();

        HttpResponse<GeocodeResumeResponse> response = executeRequest(apiRequest, GeocodeResumeResponse.class, getBodyIfDebug(apiRequest));
        return response.getData();
    }

    private GeocodeJobResponse internalGeocode(ParsedJob job, GeocodeCredentials geocodeCredentials, Address address) throws SovrenException {
        GeocodeJobRequest request = new GeocodeJobRequest();
        request.JobData = job;
        request.Provider = geocodeCredentials != null ? geocodeCredentials.Provider : GeocodeProvider.Google;
        request.ProviderKey = geocodeCredentials != null ? geocodeCredentials.ProviderKey : null;
        request.PostalAddress = address;

        RequestBody body = createJsonBody(request);
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
     * @param geocodeCredentials - The credentials you want to use for geocoding (use {@code null} for Sovren credentials)
     * @return The API response body
     * @throws SovrenException Thrown when an API error occurred
     */
    public GeocodeResumeResponse geocode(ParsedResume resume, GeocodeCredentials geocodeCredentials) throws SovrenException {
        return internalGeocode(resume, geocodeCredentials, null);
    }

    /**
     * Use this if you would like to provide an address for geocoding instead of using the one in the parsed
     * resume. The address included in the parsed resume (if present) will not be modified.
     * @param resume The resume to insert the geocoordinates (from the address) into
     * @param address The address to use to retrieve geocoordinates
     * @param geocodeCredentials - The credentials you want to use for geocoding (use {@code null} for Sovren credentials)
     * @return The API response body
     * @throws SovrenException Thrown when an API error occurred
     */
    public GeocodeResumeResponse geocode(ParsedResume resume, Address address, GeocodeCredentials geocodeCredentials) throws SovrenException {
        return internalGeocode(resume, geocodeCredentials, address);
    }

    /**
     * Uses the address in the job (if present) to look up geocoordinates and add them into the ParsedJob object.
     * These coordinates are used by the AI Searching/Matching engine.
     * @param job The job to insert the geocoordinates (from the address) into
     * @param geocodeCredentials - The credentials you want to use for geocoding (use {@code null} for Sovren credentials)
     * @return The API response body
     * @throws SovrenException Thrown when an API error occurred
     */
    public GeocodeJobResponse geocode(ParsedJob job, GeocodeCredentials geocodeCredentials) throws SovrenException {
        return internalGeocode(job, geocodeCredentials, null);
    }

    /**
     * Use this if you would like to provide an address for geocoding instead of using the one in the parsed
     * job. The address included in the parsed job (if present) will not be modified.
     * @param job The job to insert the geocoordinates (from the address) into
     * @param address The address to use to retrieve geocoordinates
     * @param geocodeCredentials - The credentials you want to use for geocoding (use {@code null} for Sovren credentials)
     * @return The API response body
     * @throws SovrenException Thrown when an API error occurred
     */
    public GeocodeJobResponse geocode(ParsedJob job, Address address, GeocodeCredentials geocodeCredentials) throws SovrenException {
        return internalGeocode(job, geocodeCredentials, address);
    }

    private GeocodeAndIndexResumeResponse internalGeocodeAndIndex(
            ParsedResume resume,
            GeocodeCredentials geocodeCredentials,
            IndexSingleDocumentInfo indexingOptions,
            boolean indexIfGeocodeFails,
            Address address,
            GeoCoordinates coordinates) throws SovrenException {
        GeocodeOptionsBase options = new GeocodeOptionsBase();
        options.Provider = geocodeCredentials != null ? geocodeCredentials.Provider : GeocodeProvider.Google;
        options.ProviderKey = geocodeCredentials != null ? geocodeCredentials.ProviderKey : null;
        options.PostalAddress = address;
        options.GeoCoordinates = coordinates;

        GeocodeAndIndexResumeRequest request = new GeocodeAndIndexResumeRequest();
        request.ResumeData = resume;
        request.GeocodeOptions = options;
        request.IndexingOptions = indexingOptions;
        request.IndexIfGeocodeFails = indexIfGeocodeFails;

        RequestBody body = createJsonBody(request);
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
            GeocodeCredentials geocodeCredentials,
            IndexSingleDocumentInfo indexingOptions,
            boolean indexIfGeocodeFails,
            Address address,
            GeoCoordinates coordinates) throws SovrenException {
        GeocodeOptionsBase options = new GeocodeOptionsBase();
        options.Provider = geocodeCredentials != null ? geocodeCredentials.Provider : GeocodeProvider.Google;
        options.ProviderKey = geocodeCredentials != null ? geocodeCredentials.ProviderKey : null;
        options.PostalAddress = address;
        options.GeoCoordinates = coordinates;

        GeocodeAndIndexJobRequest request = new GeocodeAndIndexJobRequest();
        request.JobData = job;
        request.GeocodeOptions = options;
        request.IndexingOptions = indexingOptions;
        request.IndexIfGeocodeFails = indexIfGeocodeFails;

        RequestBody body = createJsonBody(request);
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
     * @param geocodeCredentials - The credentials you want to use for geocoding (use {@code null} for Sovren credentials)
     * @return The API response body
     * @throws SovrenException Thrown when an API error occurred
     */
    public GeocodeAndIndexResumeResponse geocodeAndIndex(
            ParsedResume resume,
            IndexSingleDocumentInfo indexingOptions,
            boolean indexIfGeocodeFails,
            GeocodeCredentials geocodeCredentials) throws SovrenException {
        return internalGeocodeAndIndex(resume, geocodeCredentials, indexingOptions, indexIfGeocodeFails, null, null);
    }

    /**
     * Use this if you would like to provide an address for geocoding instead of using the one in the parsed
     * resume. The address included in the parsed resume (if present) will not be modified.
     * @param resume The resume to insert the geocoordinates (from the address) into
     * @param indexingOptions What index/document id to use to index the document after geocoding
     * @param address The address to use to retrieve geocoordinates
     * @param indexIfGeocodeFails Indicates whether or not the document should still be added to the index if the geocode request fails.
     * @param geocodeCredentials - The credentials you want to use for geocoding (use {@code null} for Sovren credentials)
     * @return The API response body
     * @throws SovrenException Thrown when an API error occurred
     */
    public GeocodeAndIndexResumeResponse geocodeAndIndex(
            ParsedResume resume,
            IndexSingleDocumentInfo indexingOptions,
            Address address,
            boolean indexIfGeocodeFails,
            GeocodeCredentials geocodeCredentials) throws SovrenException {
        return internalGeocodeAndIndex(resume, geocodeCredentials, indexingOptions, indexIfGeocodeFails, address, null);
    }

    /**
     * Use this if you already have latitude/longitude coordinates and simply wish to add them to your parsed resume.
     * The coordinates will be inserted into your parsed resume, and the address included in the
     * parsed resume (if present) will not be modified.
     * @param resume The resume to insert the geocoordinates into
     * @param indexingOptions What index/document id to use to index the document after geocoding
     * @param coordinates The geocoordinates to use
     * @param indexIfGeocodeFails Indicates whether or not the document should still be added to the index if the geocode request fails.
     * @param geocodeCredentials - The credentials you want to use for geocoding (use {@code null} for Sovren credentials)
     * @return The API response body
     * @throws SovrenException Thrown when an API error occurred
     */
    public GeocodeAndIndexResumeResponse geocodeAndIndex(
            ParsedResume resume,
            IndexSingleDocumentInfo indexingOptions,
            GeoCoordinates coordinates,
            boolean indexIfGeocodeFails,
            GeocodeCredentials geocodeCredentials) throws SovrenException {
        return internalGeocodeAndIndex(resume, geocodeCredentials, indexingOptions, indexIfGeocodeFails, null, coordinates);
    }

    /**
     * Uses the address in the job (if present) to look up geocoordinates and add them into the ParsedJob object.
     * These coordinates are used by the AI Searching/Matching engine.
     * @param job The job to geocode
     * @param indexingOptions What index/document id to use to index the document after geocoding
     * @param indexIfGeocodeFails Indicates whether or not the document should still be added to the index if the geocode request fails.
     * @param geocodeCredentials - The credentials you want to use for geocoding (use {@code null} for Sovren credentials)
     * @return The API response body
     * @throws SovrenException Thrown when an API error occurred
     */
    public GeocodeAndIndexJobResponse geocodeAndIndex(
            ParsedJob job,
            IndexSingleDocumentInfo indexingOptions,
            boolean indexIfGeocodeFails,
            GeocodeCredentials geocodeCredentials) throws SovrenException {
        return internalGeocodeAndIndex(job, geocodeCredentials, indexingOptions, indexIfGeocodeFails, null, null);
    }

    /**
     * Use this if you would like to provide an address for geocoding instead of using the one in the parsed
     * rjobesume. The address included in the parsed job (if present) will not be modified.
     * @param job The job to insert the geocoordinates (from the address) into
     * @param indexingOptions What index/document id to use to index the document after geocoding
     * @param address The address to use to retrieve geocoordinates
     * @param indexIfGeocodeFails Indicates whether or not the document should still be added to the index if the geocode request fails.
     * @param geocodeCredentials - The credentials you want to use for geocoding (use {@code null} for Sovren credentials)
     * @return The API response body
     * @throws SovrenException Thrown when an API error occurred
     */
    public GeocodeAndIndexJobResponse geocodeAndIndex(
            ParsedJob job,
            IndexSingleDocumentInfo indexingOptions,
            Address address,
            boolean indexIfGeocodeFails,
            GeocodeCredentials geocodeCredentials) throws SovrenException {
        return internalGeocodeAndIndex(job, geocodeCredentials, indexingOptions, indexIfGeocodeFails, address, null);
    }

    /**
     * Use this if you already have latitude/longitude coordinates and simply wish to add them to your parsed job.
     * The coordinates will be inserted into your parsed job, and the address included in the
     * parsed job (if present) will not be modified.
     * @param job The job to insert the geocoordinates into
     * @param indexingOptions What index/document id to use to index the document after geocoding
     * @param coordinates The geocoordinates to use
     * @param indexIfGeocodeFails Indicates whether or not the document should still be added to the index if the geocode request fails.
     * @param geocodeCredentials - The credentials you want to use for geocoding (use {@code null} for Sovren credentials)
     * @return The API response body
     * @throws SovrenException Thrown when an API error occurred
     */
    public GeocodeAndIndexJobResponse geocodeAndIndex(
            ParsedJob job,
            IndexSingleDocumentInfo indexingOptions,
            GeoCoordinates coordinates,
            boolean indexIfGeocodeFails,
            GeocodeCredentials geocodeCredentials) throws SovrenException {
        return internalGeocodeAndIndex(job, geocodeCredentials, indexingOptions, indexIfGeocodeFails, null, coordinates);
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

    /**
     * Get entire skills taxonomy
     * @param format Can either be 'json' or 'csv', determines how the data is returned
     * @param language The lanugage code that should be input to return skills taxonomy in the specified language
     * @return The API response body
     * @throws SovrenException Thrown when an API error occurs
     */
    public GetSkillsTaxonomyResponse getSkillsTaxonomy(String format, String language) throws SovrenException {
        Request apiRequest = new Request.Builder()
                .url(_endpoints.desSkillsGetTaxonomy(format,language))
                .build();

        HttpResponse<GetSkillsTaxonomyResponse> response = executeRequest(apiRequest, GetSkillsTaxonomyResponse.class, getBodyIfDebug(apiRequest));
        return response.getData();
    }

    /**
     * Specify the beginning of a word that the service will use to search skills that match the beginning of that word and return a list
     * @param prefix The text that will be used as the beginning of the skills to be searched on
     * @param limit The number of autocompleted skills that should be returned
     * @param categories The categories that the skills should be filtered to
     * @param languages The lanugage(s) that the autocompleted skills should be searched in
     * @param outputLanguage The lanugage that the autocompleted skills should be returned in
     * @return The API response body
     * @throws SovrenException Thrown when an API error occurs
     */
    public SkillsAutoCompleteResponse skillsAutoComplete(String prefix, int limit, List<String> categories, List<String> languages, String outputLanguage) throws SovrenException {
        SkillsAutoCompleteRequest request = new SkillsAutoCompleteRequest();
        request.Prefix = prefix;
        request.Limit = limit;
        request.Categories = categories;
        request.Languages = languages;
        request.OutputLanguage = outputLanguage;

        RequestBody body = createJsonBody(request);
        Request apiRequest = new Request.Builder()
            .url(_endpoints.desSkillsAutoComplete())
            .post(body)
            .build();

        HttpResponse<SkillsAutoCompleteResponse> response = executeRequest(apiRequest, SkillsAutoCompleteResponse.class, getBodyIfDebug(apiRequest));
        return response.getData();
    }

    /**
     * Provides descriptions for the requested code Ids.
     * @param codeIds The list of codes to return descriptions of skills on
     * @param categories The categories that the skills should be filtered to
     * @param outputLanguage The lanugage that the looked up skills should be returned in
     * @return The API response body
     * @throws SovrenException Thrown when an API error occurs
     */
    public SkillsLookupResponse skillsLookup(List<String> codeIds, List<String> categories, String outputLanguage) throws SovrenException {
        SkillsLookupRequest request = new SkillsLookupRequest();
        request.CodeIds = codeIds;
        request.Categories = categories;
        request.OutputLanguage = outputLanguage;

        RequestBody body = createJsonBody(request);
        Request apiRequest = new Request.Builder()
            .url(_endpoints.desSkillsLookup())
            .post(body)
            .build();

        HttpResponse<SkillsLookupResponse> response = executeRequest(apiRequest, SkillsLookupResponse.class, getBodyIfDebug(apiRequest));
        return response.getData();
    }

    /**
     * Service takes in skill strings and returns fully normalized skills with metadata.
     * @param skills The skills to return normalized skills for
     * @param language The language of the skill taxonomy to be searched on
     * @param outputLanguage The lanugage that the normalized skills should be returned in
     * @return The API response body
     * @throws SovrenException Thrown when an API error occurs
     */
    public SkillsNormalizeResponse skillsNormalize(List<String> skills, String language, String outputLanguage) throws SovrenException {
        SkillsNormalizeRequest request = new SkillsNormalizeRequest();
        request.Skills = skills;
        request.Language = language;
        request.OutputLanguage = outputLanguage;

        RequestBody body = createJsonBody(request);
        Request apiRequest = new Request.Builder()
            .url(_endpoints.desSkillsNormalize())
            .post(body)
            .build();

        HttpResponse<SkillsNormalizeResponse> response = executeRequest(apiRequest, SkillsNormalizeResponse.class, getBodyIfDebug(apiRequest));
        return response.getData();
    }

    /**
     * Service extracts the known skills from the input text
     * @param text The text to extract skills from
     * @param language The language of the input text
     * @param outputLanguage The lanugage that the normalized skills should be returned in
     * @param threshold The minimum confidence trheshold for including a skill in the response float from 0 - 1 
     * @return The API response body
     * @throws SovrenException Thrown when an API error occurs
     */
    public SkillsExtractResponse skillsExtract(String text, String language, float threshold, String outputLanguage) throws SovrenException {
        SkillsExtractRequest request = new SkillsExtractRequest();
        request.Text = text;
        request.Language = language;
        request.OutputLanguage = outputLanguage;
        request.Threshold = threshold;

        RequestBody body = createJsonBody(request);
        Request apiRequest = new Request.Builder()
            .url(_endpoints.desSkillsExtract())
            .post(body)
            .build();

        HttpResponse<SkillsExtractResponse> response = executeRequest(apiRequest, SkillsExtractResponse.class, getBodyIfDebug(apiRequest));
        return response.getData();
    }

    /**
     * Get entire professions taxonomy
     * @param format Can either be 'json' or 'csv', determines how the data is returned
     * @param language The lanugage code that should be input to return professions taxonomy in the specified language
     * @return The API response body
     * @throws SovrenException Thrown when an API error occurs
     */
    public GetProfessionsTaxonomyResponse getProfessionsTaxonomy(String format, String language) throws SovrenException {
        Request apiRequest = new Request.Builder()
                .url(_endpoints.desProfessionsGetTaxonomy(format,language))
                .build();

        HttpResponse<GetProfessionsTaxonomyResponse> response = executeRequest(apiRequest, GetProfessionsTaxonomyResponse.class, getBodyIfDebug(apiRequest));
        return response.getData();
    }

    /**
     * Specify the beginning of a word that the service will use to search professions that match the beginning of that word and return a list
     * @param prefix The text that will be used as the beginning of the professions to be searched on
     * @param limit The number of autocompleted professions that should be returned
     * @param categories The categories that the professions should be filtered to
     * @param languages The lanugage(s) that the autocompleted professions should be searched in
     * @param outputLanguage The lanugage that the autocompleted professions should be returned in
     * @return The API response body
     * @throws SovrenException Thrown when an API error occurs
     */
    public ProfessionsAutoCompleteResponse professionsAutoComplete(String prefix, int limit, List<String> categories, List<String> languages, String outputLanguage) throws SovrenException {
        ProfessionsAutoCompleteRequest request = new ProfessionsAutoCompleteRequest();
        request.Prefix = prefix;
        request.Limit = limit;
        request.Categories = categories;
        request.Languages = languages;
        request.OutputLanguage = outputLanguage;

        RequestBody body = createJsonBody(request);
        Request apiRequest = new Request.Builder()
            .url(_endpoints.desProfessionsAutoComplete())
            .post(body)
            .build();

        HttpResponse<ProfessionsAutoCompleteResponse> response = executeRequest(apiRequest, ProfessionsAutoCompleteResponse.class, getBodyIfDebug(apiRequest));
        return response.getData();
    }

    /**
     * Service takes in job titles array of strings and returns fully normalized professions with metadata.
     * @param jobTitles The job titles to return normalized professions for
     * @param language The language of the professions taxonomy to be searched on
     * @param outputLanguage The lanugage that the normalized professions should be returned in
     * @return The API response body
     * @throws SovrenException Thrown when an API error occurs
     */
    public ProfessionsNormalizeResponse professionsNormalize(List<String> jobTitles, String language, String outputLanguage) throws SovrenException {
        ProfessionsNormalizeRequest request = new ProfessionsNormalizeRequest();
        request.JobTitles = jobTitles;
        request.Language = language;
        request.OutputLanguage = outputLanguage;

        RequestBody body = createJsonBody(request);
        Request apiRequest = new Request.Builder()
            .url(_endpoints.desProfessionsNormalize())
            .post(body)
            .build();

        HttpResponse<ProfessionsNormalizeResponse> response = executeRequest(apiRequest, ProfessionsNormalizeResponse.class, getBodyIfDebug(apiRequest));
        return response.getData();
    }

    /**
     * Provides descriptions for the requested code Ids.
     * @param codeIds The list of codes to return descriptions of professions on
     * @param outputLanguage The language that the looked up professions should be returned in
     * @return The API response body
     * @throws SovrenException Thrown when an API error occurs
     */
    public ProfessionsLookupResponse professionsLookup(List<String> codeIds, String outputLanguage) throws SovrenException {
        ProfessionsLookupRequest request = new ProfessionsLookupRequest();
        request.CodeIds = codeIds;
        request.OutputLanguage = outputLanguage;

        RequestBody body = createJsonBody(request);
        Request apiRequest = new Request.Builder()
            .url(_endpoints.desProfessionsLookup())
            .post(body)
            .build();

        HttpResponse<ProfessionsLookupResponse> response = executeRequest(apiRequest, ProfessionsLookupResponse.class, getBodyIfDebug(apiRequest));
        return response.getData();
    }

    /**
     * Service allows a user to compare two given professions with respect to their related skills
     * @param codeIds The list of profession codes to compare skills against
     * @return The API response body
     * @throws SovrenException Thrown when an API error occurs
     */
    public CompareSkillsResponse compareSkills(List<String> codeIds) throws SovrenException {
        CompareSkillsRequest request = new CompareSkillsRequest();
        request.CodeIds = codeIds;

        RequestBody body = createJsonBody(request);
        Request apiRequest = new Request.Builder()
            .url(_endpoints.desOntologyCompareSkills())
            .post(body)
            .build();

        HttpResponse<CompareSkillsResponse> response = executeRequest(apiRequest, CompareSkillsResponse.class, getBodyIfDebug(apiRequest));
        return response.getData();
    }

    /**
     * Service allows a user to compare the skill set of a candidate to the skill set related to a target profession
     * @param skillCodeIds The list of skill codes that should be compared against the given profession
     * @param professionCodeId The profession code that the skills should be compared to
     * @return The API response body
     * @throws SovrenException Thrown when an API error occurs
     */
    public CompareSkillsToProfessionsResponse compareSkillsToProfessions(List<String> skillCodeIds, String professionCodeId) throws SovrenException {
        CompareSkillsToProfessionsRequest request = new CompareSkillsToProfessionsRequest();
        request.SkillCodeIds = skillCodeIds;
        request.ProfessionCodeId = professionCodeId;

        RequestBody body = createJsonBody(request);
        Request apiRequest = new Request.Builder()
            .url(_endpoints.desOntologyCompareSkillsToProfessions())
            .post(body)
            .build();

        HttpResponse<CompareSkillsToProfessionsResponse> response = executeRequest(apiRequest, CompareSkillsToProfessionsResponse.class, getBodyIfDebug(apiRequest));
        return response.getData();
    }

    /**
     * Service returns skills related to a given profession
     * @param codeIds The list of profession codes for which the service should return related skills
     * @param limit The maximum amount of suggested skills that should be returned
     * @return The API response body
     * @throws SovrenException Thrown when an API error occurs
     */
    public SuggestSkillsResponse suggestSkills(List<String> codeIds, int limit) throws SovrenException {
        SuggestSkillsRequest request = new SuggestSkillsRequest();
        request.CodeIds = codeIds;
        request.Limit = limit;

        RequestBody body = createJsonBody(request);
        Request apiRequest = new Request.Builder()
            .url(_endpoints.desOntologySuggestSkills())
            .post(body)
            .build();

        HttpResponse<SuggestSkillsResponse> response = executeRequest(apiRequest, SuggestSkillsResponse.class, getBodyIfDebug(apiRequest));
        return response.getData();
    }

    /**
     * Service returns suggested professions based off on an input set of skills
     * @param codeIds The list of skill codes for which the service should return suggested professions
     * @param limit The maximum amount of suggested professions that should be returned
     * @param returnMissingSkills A boolean flag that tells the service whether or not to return skills that were missing from the input set of skills that are necessary for the suggested profession
     * @return The API response body
     * @throws SovrenException Thrown when an API error occurs
     */
    public SuggestProfessionsResponse suggestProfessions(List<String> codeIds, int limit, boolean returnMissingSkills) throws SovrenException {
        SuggestProfessionsRequest request = new SuggestProfessionsRequest();
        request.CodeIds = codeIds;
        request.Limit = limit;
        request.ReturnMissingSkills = returnMissingSkills;

        RequestBody body = createJsonBody(request);
        Request apiRequest = new Request.Builder()
            .url(_endpoints.desOntologySuggestProfessions())
            .post(body)
            .build();

        HttpResponse<SuggestProfessionsResponse> response = executeRequest(apiRequest, SuggestProfessionsResponse.class, getBodyIfDebug(apiRequest));
        return response.getData();
    }
}