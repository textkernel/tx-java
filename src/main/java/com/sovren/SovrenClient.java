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
import com.sovren.models.api.dataenrichment.AutocompleteRequest;
import com.sovren.models.api.dataenrichment.GetMetadataResponse;
import com.sovren.models.api.dataenrichment.TaxonomyFormat;
import com.sovren.models.api.dataenrichment.skills.response.GetSkillsTaxonomyResponseValue;
import com.sovren.models.api.dataenrichment.professions.response.GetProfessionsTaxonomyResponseValue;
import com.sovren.models.api.dataenrichment.ontology.request.CompareProfessionsRequest;
import com.sovren.models.api.dataenrichment.ontology.request.CompareSkillsToProfessionRequest;
import com.sovren.models.api.dataenrichment.ontology.request.SuggestProfessionsRequest;
import com.sovren.models.api.dataenrichment.ontology.request.SuggestSkillsRequest;
import com.sovren.models.api.dataenrichment.ontology.response.CompareProfessionsResponse;
import com.sovren.models.api.dataenrichment.ontology.response.CompareSkillsToProfessionResponse;
import com.sovren.models.api.dataenrichment.ontology.response.SuggestProfessionsResponse;
import com.sovren.models.api.dataenrichment.ontology.response.SuggestSkillsResponse;
import com.sovren.models.api.dataenrichment.professions.request.LookupProfessionCodesRequest;
import com.sovren.models.api.dataenrichment.professions.request.NormalizeProfessionsRequest;
import com.sovren.models.api.dataenrichment.professions.response.GetProfessionsTaxonomyResponse;
import com.sovren.models.api.dataenrichment.professions.response.LookupProfessionCodesResponse;
import com.sovren.models.api.dataenrichment.professions.response.NormalizeProfessionsResponse;
import com.sovren.models.api.dataenrichment.professions.response.ProfessionsAutoCompleteResponse;
import com.sovren.models.api.dataenrichment.skills.request.ExtractSkillsRequest;
import com.sovren.models.api.dataenrichment.skills.request.LookupSkillsRequest;
import com.sovren.models.api.dataenrichment.skills.request.NormalizeSkillsRequest;
import com.sovren.models.api.dataenrichment.skills.request.SkillsAutoCompleteRequest;
import com.sovren.models.api.dataenrichment.skills.response.AutoCompleteSkillsResponse;
import com.sovren.models.api.dataenrichment.skills.response.ExtractSkillsResponse;
import com.sovren.models.api.dataenrichment.skills.response.GetSkillsTaxonomyResponse;
import com.sovren.models.api.dataenrichment.skills.response.LookupSkillCodesResponse;
import com.sovren.models.api.dataenrichment.skills.response.NormalizeSkillsResponse;
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
import com.sovren.models.resume.employment.Position;
import com.sovren.models.resume.skills.ResumeNormalizedSkill;

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
import java.util.ArrayList;
import java.util.Arrays;
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
     * Get all skills in the taxonomy with associated IDs and descriptions in all supported languages.
     * @param format The format of the returned taxonomy. <br>NOTE: if you set this to {@link TaxonomyFormat#csv}, only the {@link GetSkillsTaxonomyResponseValue#CsvOutput} will be populated.
     * @return The API response body
     * @throws SovrenException Thrown when an API error occurs
     */
    public GetSkillsTaxonomyResponse getSkillsTaxonomy(TaxonomyFormat format) throws SovrenException {
        Request apiRequest = new Request.Builder()
                .url(_endpoints.desSkillsGetTaxonomy(format))
                .build();

        HttpResponse<GetSkillsTaxonomyResponse> response = executeRequest(apiRequest, GetSkillsTaxonomyResponse.class, getBodyIfDebug(apiRequest));
        return response.getData();
    }

    /**
     * Get all skills in the taxonomy with associated IDs and descriptions in all supported languages.
     * @return The API response body
     * @throws SovrenException Thrown when an API error occurs
     */
    public GetSkillsTaxonomyResponse getSkillsTaxonomy() throws SovrenException {
        return getSkillsTaxonomy(TaxonomyFormat.json);
    }

    /**
     * Get metadata about the skills taxonomy/service.
     * @return The API response body
     * @throws SovrenException Thrown when an API error occurs
     */
    public GetMetadataResponse getSkillsTaxonomyMetadata() throws SovrenException {
        Request apiRequest = new Request.Builder()
                .url(_endpoints.desSkillsGetMetadata())
                .build();

        HttpResponse<GetMetadataResponse> response = executeRequest(apiRequest, GetMetadataResponse.class, getBodyIfDebug(apiRequest));
        return response.getData();
    }

    /**
     * Returns normalized skills that begin with a given prefix, based on the chosen language(s). Each skill is associated with multiple descriptions. If any of the descriptions are a good completion of the given prefix, the skill is included in the results.
     * @param prefix The skill prefix to be completed. Must contain at least 1 character.
     * @param languages The language(s) used to search for matching skills (the language of the provided Prefix). A maximum of 5 languages can be provided. Must be one of the supported <a href="https://sovren.com/technical-specs/latest/rest-api/data-enrichment-services/overview/#skills-languages">ISO codes</a>.<br>Default is 'en' only.
     * @param outputLanguage The language to ouput the found skill descriptions in (default is 'en'). Must be one of the supported <a href="https://sovren.com/technical-specs/latest/rest-api/data-enrichment-services/overview/#skills-languages">ISO codes</a>.
     * @param types If specified, only these types of skills will be returned. The following values are acceptable: Professional, IT, Language, Soft, All.
     * @param limit The maximum number of returned skills. The default is 10 and the maximum is 100.
     * @return The API response body
     * @throws SovrenException Thrown when an API error occurs
     */
    public AutoCompleteSkillsResponse autocompleteSkill(String prefix, List<String> languages, String outputLanguage, List<String> types, int limit) throws SovrenException {
        SkillsAutoCompleteRequest request = new SkillsAutoCompleteRequest();
        request.Prefix = prefix;
        request.Limit = limit;
        request.Types = types;
        request.Languages = languages;
        request.OutputLanguage = outputLanguage;

        RequestBody body = createJsonBody(request);
        Request apiRequest = new Request.Builder()
            .url(_endpoints.desSkillsAutoComplete())
            .post(body)
            .build();

        HttpResponse<AutoCompleteSkillsResponse> response = executeRequest(apiRequest, AutoCompleteSkillsResponse.class, getBodyIfDebug(apiRequest));
        return response.getData();
    }

    /**
     * Returns normalized skills that begin with a given prefix, based on the chosen language(s). Each skill is associated with multiple descriptions. If any of the descriptions are a good completion of the given prefix, the skill is included in the results.
     * @param prefix The skill prefix to be completed. Must contain at least 1 character.
     * @return The API response body
     * @throws SovrenException Thrown when an API error occurs
     */
    public AutoCompleteSkillsResponse autocompleteSkill(String prefix) throws SovrenException {
        return autocompleteSkill(prefix,null,null,null,10);
    }

    /**
     * Get the details associated with given skills in the taxonomy.
     * @param skillIds The IDs of the skills to get details about. A maximum of 100 IDs can be requested.
     * @param outputLanguage The language to use for the output skill descriptions. If not provided, defaults to en. If specified, must be one of the supported <a href="https://sovren.com/technical-specs/latest/rest-api/data-enrichment-services/overview/#skills-languages">ISO codes</a>.<br>Default is 'en'.
     * @return The API response body
     * @throws SovrenException Thrown when an API error occurs
     */
    public LookupSkillCodesResponse lookupSkills(List<String> skillIds, String outputLanguage) throws SovrenException {
        LookupSkillsRequest request = new LookupSkillsRequest();
        request.SkillIds = skillIds;
        request.OutputLanguage = outputLanguage;

        RequestBody body = createJsonBody(request);
        Request apiRequest = new Request.Builder()
            .url(_endpoints.desSkillsLookup())
            .post(body)
            .build();

        HttpResponse<LookupSkillCodesResponse> response = executeRequest(apiRequest, LookupSkillCodesResponse.class, getBodyIfDebug(apiRequest));
        return response.getData();
    }

    /**
     * Get the details associated with given skills in the taxonomy.
     * @param skillIds The IDs of the skills to get details about. A maximum of 100 IDs can be requested.
     * @return The API response body
     * @throws SovrenException Thrown when an API error occurs
     */
    public LookupSkillCodesResponse lookupSkills(List<String> skillIds) throws SovrenException {
        return lookupSkills(skillIds,null);
    }

    /**
     * Normalize the given skills to the most closely-related skills in the taxonomy.
     * @param skills The list of skills to normalize (up to 50 skills, each skill may not exceed 100 characters).
     * @param language The language of the given skills. Must be one of the supported <a href="https://sovren.com/technical-specs/latest/rest-api/data-enrichment-services/overview/#skills-languages">ISO codes</a>.<br>Default is 'en'.
     * @param outputLanguage The language to use for the output skill descriptions. Must be one of the supported <a href="https://sovren.com/technical-specs/latest/rest-api/data-enrichment-services/overview/#skills-languages">ISO codes</a>.<br>Defaults to whatever is used for the 'language' parameter.
     * @return The API response body
     * @throws SovrenException Thrown when an API error occurs
     */
    public NormalizeSkillsResponse normalizeSkills(List<String> skills, String language, String outputLanguage) throws SovrenException {
        NormalizeSkillsRequest request = new NormalizeSkillsRequest();
        request.Skills = skills;
        request.Language = language;
        request.OutputLanguage = outputLanguage;

        RequestBody body = createJsonBody(request);
        Request apiRequest = new Request.Builder()
            .url(_endpoints.desSkillsNormalize())
            .post(body)
            .build();

        HttpResponse<NormalizeSkillsResponse> response = executeRequest(apiRequest, NormalizeSkillsResponse.class, getBodyIfDebug(apiRequest));
        return response.getData();
    }

    /**
     * Normalize the given skills to the most closely-related skills in the taxonomy.
     * @param skills The list of skills to normalize (up to 50 skills, each skill may not exceed 100 characters).
     * @return The API response body
     * @throws SovrenException Thrown when an API error occurs
     */
    public NormalizeSkillsResponse normalizeSkills(List<String> skills) throws SovrenException {
        return normalizeSkills(skills,null,null);
    }

    /**
     * Extracts known skills from the given text.
     * @param text The text to extract skills from. There is a 24,000 character limit.
     * @param language The language of the input text. Must be one of the supported <a href="https://sovren.com/technical-specs/latest/rest-api/data-enrichment-services/overview/#skills-languages">ISO codes</a>.<br>Default is 'en'.
     * @param outputLanguage The language to use for the output skill descriptions. If not provided, defaults to the input language. Must be one of the supported <a href="https://sovren.com/technical-specs/latest/rest-api/data-enrichment-services/overview/#skills-languages">ISO codes</a>.
     * @param threshold A value from [0 - 1] for the minimum confidence threshold for extracted skills. Lower values will return more skills, but also increase the likelihood of ambiguity-related errors. The recommended and default value is 0.5.
     * @return The API response body
     * @throws SovrenException Thrown when an API error occurs
     */
    public ExtractSkillsResponse extractSkills(String text, String language, String outputLanguage, float threshold) throws SovrenException {
        ExtractSkillsRequest request = new ExtractSkillsRequest();
        request.Text = text;
        request.Language = language;
        request.OutputLanguage = outputLanguage;
        request.Threshold = threshold;

        RequestBody body = createJsonBody(request);
        Request apiRequest = new Request.Builder()
            .url(_endpoints.desSkillsExtract())
            .post(body)
            .build();

        HttpResponse<ExtractSkillsResponse> response = executeRequest(apiRequest, ExtractSkillsResponse.class, getBodyIfDebug(apiRequest));
        return response.getData();
    }

    /**
     * Extracts known skills from the given text.
     * @param text The text to extract skills from. There is a 24,000 character limit.
     * @return The API response body
     * @throws SovrenException Thrown when an API error occurs
     */
    public ExtractSkillsResponse extractSkills(String text) throws SovrenException {
        return extractSkills(text,null,null,0.5f);
    }

    /**
     * Get all professions in the taxonomy with associated IDs and descriptions in all supported languages.
     * @param language The language parameter returns the taxonomy with descriptions only in that specified language. If not specified, descriptions in all languages are returned. Must be specified as one of the supported <a href="https://sovren.com/technical-specs/latest/rest-api/data-enrichment-services/overview/#professions-languages">ISO codes</a>.
     * @param format The format of the returned taxonomy. <br>NOTE: if you set this to {@link TaxonomyFormat#csv}, only the {@link GetProfessionsTaxonomyResponseValue#CsvOutput} will be populated.
     * @return The API response body
     * @throws SovrenException Thrown when an API error occurs
     */
    public GetProfessionsTaxonomyResponse getProfessionsTaxonomy(String language, TaxonomyFormat format) throws SovrenException {
        Request apiRequest = new Request.Builder()
                .url(_endpoints.desProfessionsGetTaxonomy(format,language))
                .build();

        HttpResponse<GetProfessionsTaxonomyResponse> response = executeRequest(apiRequest, GetProfessionsTaxonomyResponse.class, getBodyIfDebug(apiRequest));
        return response.getData();
    }

    /**
     * Get all professions in the taxonomy with associated IDs and descriptions in all supported languages.
     * @return The API response body
     * @throws SovrenException Thrown when an API error occurs
     */
    public GetProfessionsTaxonomyResponse getProfessionsTaxonomy() throws SovrenException {
        return getProfessionsTaxonomy(null,TaxonomyFormat.json);
    }

    /**
     * Get metadata about the professions taxonomy/service.
     * @return The API response body
     * @throws SovrenException Thrown when an API error occurs
     */
    public GetMetadataResponse getProfessionsTaxonomyMetadata() throws SovrenException {
        Request apiRequest = new Request.Builder()
                .url(_endpoints.desProfessionsGetMetadata())
                .build();

        HttpResponse<GetMetadataResponse> response = executeRequest(apiRequest, GetMetadataResponse.class, getBodyIfDebug(apiRequest));
        return response.getData();
    }

    /**
     * Returns normalized professions that begin with a given prefix, based on the chosen language(s). Each profession is associated with multiple descriptions. If any of the descriptions are a good completion of the given prefix, the profession is included in the results.
     * @param prefix The job title prefix to be completed. Must contain at least 1 character.
     * @param languages The language(s) used to search for matching professions (the language of the provided Prefix). A maximum of 5 languages can be provided. Must be one of the supported <a href="https://sovren.com/technical-specs/latest/rest-api/data-enrichment-services/overview/#professions-languages">ISO codes</a>. Default is 'en' only.
     * @param outputLanguage The language to ouput the found professions in (default is 'en'). Must be one of the supported <a href="https://sovren.com/technical-specs/latest/rest-api/data-enrichment-services/overview/#professions-languages">ISO codes</a>.
     * @param limit The maximum number of returned professions. The default is 10 and the maximum is 100.
     * @return The API response body
     * @throws SovrenException Thrown when an API error occurs
     */
    public ProfessionsAutoCompleteResponse autocompleteProfession(String prefix, List<String> languages, String outputLanguage, int limit) throws SovrenException {
        AutocompleteRequest request = new AutocompleteRequest();
        request.Prefix = prefix;
        request.Limit = limit;
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
     * Returns normalized professions that begin with a given prefix, based on the default language of english. Each profession is associated with multiple descriptions. If any of the descriptions are a good completion of the given prefix, the profession is included in the results.
     * @param prefix The job title prefix to be completed. Must contain at least 1 character.
     * @return The API response body
     * @throws SovrenException Thrown when an API error occurs
     */
    public ProfessionsAutoCompleteResponse autocompleteProfession(String prefix) throws SovrenException {
        List<String> languages = new ArrayList<>();
        languages.add("en");
        return  autocompleteProfession(prefix,languages,"en",10);
    }

    /**
     * Normalize the given job titles to the most closely-related professions in the taxonomy.
     * @param jobTitles The list of job titles to normalize (up to 10 job titles, each job title may not exceed 400 characters).
     * @param language The language of the input job titles. Must be one of the supported <a href="https://sovren.com/technical-specs/latest/rest-api/data-enrichment-services/overview/#professions-languages">ISO codes</a>.<br>Default is 'en'.
     * @param outputLanguage The language to use for descriptions of the returned normalized professions. Must be one of the supported <a href="https://sovren.com/technical-specs/latest/rest-api/data-enrichment-services/overview/#professions-languages">ISO codes</a>.<br>Defaults to whatever is used for the 'language' parameter.
     * @return The API response body
     * @throws SovrenException Thrown when an API error occurs
     */
    public NormalizeProfessionsResponse normalizeProfessions(List<String> jobTitles, String language, String outputLanguage) throws SovrenException {
        NormalizeProfessionsRequest request = new NormalizeProfessionsRequest();
        request.JobTitles = jobTitles;
        request.Language = language;
        request.OutputLanguage = outputLanguage;

        RequestBody body = createJsonBody(request);
        Request apiRequest = new Request.Builder()
            .url(_endpoints.desProfessionsNormalize())
            .post(body)
            .build();

        HttpResponse<NormalizeProfessionsResponse> response = executeRequest(apiRequest, NormalizeProfessionsResponse.class, getBodyIfDebug(apiRequest));
        return response.getData();
    }

    /**
     * Normalize the given job titles to the most closely-related professions in the taxonomy.
     * @param jobTitles The list of job titles to normalize (up to 10 job titles, each job title may not exceed 400 characters).
     * @return The API response body
     * @throws SovrenException Thrown when an API error occurs
     */
    public NormalizeProfessionsResponse normalizeProfessions(List<String> jobTitles) throws SovrenException {
        return normalizeProfessions(jobTitles,null,null);
    }

    /**
     * Get details for the given professions in the taxonomy.
     * @param codeIds The profession code IDs to get details about from the <a href="https://sovren.com/technical-specs/latest/rest-api/data-enrichment-services/overview/#professions-taxonomies">Sovren Professions Taxonomy</a>.
     * @param outputLanguage The language to use for professions descriptions (default is en). Must be an allowed <a href="https://sovren.com/technical-specs/latest/rest-api/data-enrichment-services/overview/#professions-languages">ISO code</a>. <br>Default is 'en'.
     * @return The API response body
     * @throws SovrenException Thrown when an API error occurs
     */
    public LookupProfessionCodesResponse lookupProfessions(List<Integer> codeIds, String outputLanguage) throws SovrenException {
        LookupProfessionCodesRequest request = new LookupProfessionCodesRequest();
        request.CodeIds = codeIds;
        request.OutputLanguage = outputLanguage;

        RequestBody body = createJsonBody(request);
        Request apiRequest = new Request.Builder()
            .url(_endpoints.desProfessionsLookup())
            .post(body)
            .build();

        HttpResponse<LookupProfessionCodesResponse> response = executeRequest(apiRequest, LookupProfessionCodesResponse.class, getBodyIfDebug(apiRequest));
        return response.getData();
    }

    /**
     * Get details for the given professions in the taxonomy.
     * @param codeIds The profession code IDs to get details about from the <a href="https://sovren.com/technical-specs/latest/rest-api/data-enrichment-services/overview/#professions-taxonomies">Sovren Professions Taxonomy</a>.
     * @return The API response body
     * @throws SovrenException Thrown when an API error occurs
     */
    public LookupProfessionCodesResponse lookupProfessions(List<Integer> codeIds) throws SovrenException {
        return lookupProfessions(codeIds,null);
    }

    /**
     * Compare two professions based on the skills associated with each.
     * @param profession1 A profession code ID from the <a href="https://sovren.com/technical-specs/latest/rest-api/data-enrichment-services/overview/#professions-taxonomies">Sovren Professions Taxonomy</a> to compare.
     * @param profession2 A profession code ID from the <a href="https://sovren.com/technical-specs/latest/rest-api/data-enrichment-services/overview/#professions-taxonomies">Sovren Professions Taxonomy</a> to compare.
     * @return The API response body
     * @throws SovrenException Thrown when an API error occurs
     */
    public CompareProfessionsResponse compareProfessions(int profession1, int profession2) throws SovrenException {
        CompareProfessionsRequest request = new CompareProfessionsRequest();
        request.ProfessionCodeIds = new ArrayList<Integer>();
        request.ProfessionCodeIds.add(profession1);
        request.ProfessionCodeIds.add(profession2);

        RequestBody body = createJsonBody(request);
        Request apiRequest = new Request.Builder()
            .url(_endpoints.desOntologyCompareProfessions())
            .post(body)
            .build();

        HttpResponse<CompareProfessionsResponse> response = executeRequest(apiRequest, CompareProfessionsResponse.class, getBodyIfDebug(apiRequest));
        return response.getData();
    }

    /**
     * Compare a given set of skills to the skills related to a given profession.
     * @param professionCodeId The profession code ID from the <a href="https://sovren.com/technical-specs/latest/rest-api/data-enrichment-services/overview/#professions-taxonomies">Sovren Professions Taxonomy</a> to compare the skill set to.
     * @param skillIds The skill IDs which should be compared against the given profession. The list can contain up to 50 skills.
     * @return The API response body
     * @throws SovrenException Thrown when an API error occurs
     */
    public CompareSkillsToProfessionResponse compareSkillsToProfessions(int professionCodeId, String... skillIds) throws SovrenException {
        CompareSkillsToProfessionRequest request = new CompareSkillsToProfessionRequest();
        request.SkillIds = new ArrayList<String>();
        List<String> newList = Arrays.asList(skillIds);
        int amountOfSkills = newList.size() > 50 ? 50 : newList.size();

        for(int i = 0; i < amountOfSkills; i++) {
            request.SkillIds.add(newList.get(i));
        };
        request.ProfessionCodeId = professionCodeId;

        RequestBody body = createJsonBody(request);
        Request apiRequest = new Request.Builder()
            .url(_endpoints.desOntologyCompareSkillsToProfession())
            .post(body)
            .build();

        HttpResponse<CompareSkillsToProfessionResponse> response = executeRequest(apiRequest, CompareSkillsToProfessionResponse.class, getBodyIfDebug(apiRequest));
        return response.getData();
    }

    /**
     * Compare the skills of a candidate to the skills related to a job using the Ontology API.
     * @param resume The resume containing the skills of the candidate
     * @param professionCodeId The profession code ID from the <a href="https://sovren.com/technical-specs/latest/rest-api/data-enrichment-services/overview/#professions-taxonomies">Sovren Professions Taxonomy</a> to compare the skill set to.
     * @return The API response body
     * @throws SovrenException Thrown when an API error occurs
     */
    public CompareSkillsToProfessionResponse compareSkillsToProfessions(ParsedResume resume, int professionCodeId) throws SovrenException {
        if(resume != null && resume.Skills != null && resume.Skills.Normalized != null && resume.Skills.Normalized.size() > 0){
            String[] skillIds = new String[resume.Skills.Normalized.size()];
            for(int i = 0; i < resume.Skills.Normalized.size(); i++) {
                skillIds[i] = resume.Skills.Normalized.get(i).Id;
            }

            return compareSkillsToProfessions(professionCodeId,skillIds);
        }
        throw new IllegalArgumentException("The resume must be parsed with V2 skills selected, and with skills normalization enabled");
    }

    /**
     * Suggests skills related to given professions. The service returns salient skills that are strongly associated with the professions.
     * @param professionCodeIds  The code IDs of the professions to suggest skills for.
     * @param limit The maximum amount of suggested skills returned. The maximum amount allowed is 10. If not sure what value should be, provide 10 as default limit.
     * @return The API response body
     * @throws SovrenException Thrown when an API error occurs
     */
    public SuggestSkillsResponse suggestSkills(List<Integer> professionCodeIds, int limit) throws SovrenException {
        SuggestSkillsRequest request = new SuggestSkillsRequest();
        request.ProfessionCodeIds = professionCodeIds;
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
     * Suggests skills related to given professions. The service returns salient skills that are strongly associated with the professions.
     * @param professionCodeIds  The code IDs of the professions to suggest skills for.
     * @return The API response body
     * @throws SovrenException Thrown when an API error occurs
     */
    public SuggestSkillsResponse suggestSkills(List<Integer> professionCodeIds) throws SovrenException {
        return suggestSkills(professionCodeIds, 10);
    }

    /**
     * Suggests skills related to a resume based on the recent professions in the resume.
     * @param resume The resume to suggest skills for (based on the professions in the resume).
     * @param limit The maximum amount of suggested skills returned. The maximum amount allowed is 10. If not sure what value should be, provide 10 as default limit.
     * @return The API response body
     * @throws SovrenException Thrown when an API error occurs
     */
    public SuggestSkillsResponse suggestSkills(ParsedResume resume, int limit) throws SovrenException {
        if(resume != null && resume.EmploymentHistory != null && resume.EmploymentHistory.Positions != null){
            List<Integer> normalizedProfs = new ArrayList<Integer>();
            for(Position position: resume.EmploymentHistory.Positions){
                if (position != null && position.NormalizedProfession != null && position.NormalizedProfession.Profession != null && position.NormalizedProfession.Profession.CodeId != null){
                    normalizedProfs.add(position.NormalizedProfession.Profession.CodeId);
                }
            }

            if (normalizedProfs.size() > 0){
                return suggestSkills(normalizedProfs,limit);
            }
        }
        throw new IllegalArgumentException("No professions were found in the resume, or the resume was parsed without professions normalization enabled");
    }

    /**
     * Suggests skills related to a resume based on the recent professions in the resume.
     * @param resume The resume to suggest skills for (based on the professions in the resume).
     * @return The API response body
     * @throws SovrenException Thrown when an API error occurs
     */
    public SuggestSkillsResponse suggestSkills(ParsedResume resume) throws SovrenException {
        return suggestSkills(resume, 10);
    }

    /**
     * Suggests skills related to a job based on the profession title in the job.
     * @param job The resume to suggest skills for (based on the professions in the resume).
     * @param limit The maximum amount of suggested skills returned. The maximum amount allowed is 10. If not sure what value should be, provide 10 as default limit.
     * @return The API response body
     * @throws SovrenException Thrown when an API error occurs
     */
    public SuggestSkillsResponse suggestSkills(ParsedJob job, int limit) throws SovrenException {
        if(job != null && job.JobTitles != null && job.JobTitles.NormalizedProfession != null && job.JobTitles.NormalizedProfession.Profession != null && job.JobTitles.NormalizedProfession.Profession.CodeId != null){
            List<Integer> ids = new ArrayList<Integer>();
            ids.add(job.JobTitles.NormalizedProfession.Profession.CodeId);

            return suggestSkills(ids,limit);
        }
        throw new IllegalArgumentException("No professions were found in the job, or the job was parsed without professions normalization enabled");
    }

    /**
     * Suggests skills related to a job based on the profession title in the job.
     * @param job The resume to suggest skills for (based on the professions in the resume).
     * @return The API response body
     * @throws SovrenException Thrown when an API error occurs
     */
    public SuggestSkillsResponse suggestSkills(ParsedJob job) throws SovrenException {
        return suggestSkills(job, 10);
    }

    /**
     * Suggest professions based on the <b>skills</b> within a given resume.
     * @param resume The professions are suggested based on the <b>skills</b> within this resume.
     * @param limit The maximum amount of professions returned. If not sure what value should be, provide 10 as default limit.
     * @param returnMissingSkills Flag to enable returning a list of missing skills per suggested profession.
     * @return The API response body
     * @throws SovrenException Thrown when an API error occurs
     */
    public SuggestProfessionsResponse suggestProfessions(ParsedResume resume, int limit, boolean returnMissingSkills) throws SovrenException {
        if(resume != null && resume.Skills != null && resume.Skills.Normalized != null && resume.Skills.Normalized.size() > 0){
            List<String> ids = new ArrayList<String>();
            for(ResumeNormalizedSkill skill: resume.Skills.Normalized){
                ids.add(skill.Id);   
            } 

            return suggestProfessions(ids,limit,returnMissingSkills);
        }
        throw new IllegalArgumentException("The resume must be parsed with V2 skills selected, and with skills normalization enabled.");
    }

    /**
     * Suggest professions based on the <b>skills</b> within a given resume.
     * @param resume The professions are suggested based on the <b>skills</b> within this resume. Defaults limit returned to 10 and does not return missing skills. Use another overload to specify these parameters.
     * @return The API response body
     * @throws SovrenException Thrown when an API error occurs
     */
    public SuggestProfessionsResponse suggestProfessions(ParsedResume resume) throws SovrenException {
        return suggestProfessions(resume,10,false);
    }

    /**
     * Suggest professions based on the <b>skills</b> within a given job.
     * @param job The professions are suggested based on the <b>skills</b> within this job.
     * @param limit The maximum amount of professions returned. If not sure what value should be, provide 10 as default limit.
     * @param returnMissingSkills Flag to enable returning a list of missing skills per suggested profession.
     * @return The API response body
     * @throws SovrenException Thrown when an API error occurs
     */
    public SuggestProfessionsResponse suggestProfessions(ParsedJob job, int limit, boolean returnMissingSkills) throws SovrenException {
        if(job != null && job.Skills != null && job.Skills.Normalized != null && job.Skills.Normalized.size() > 0){
            List<String> ids = new ArrayList<String>();
            int amountOfSkills = job.Skills.Normalized.size() > 50 ? 50 : job.Skills.Normalized.size();
            for(int i = 0; i < amountOfSkills; i++) {
                ids.add(job.Skills.Normalized.get(i).Id);
            }

            return suggestProfessions(ids,limit,returnMissingSkills);
        }
        throw new IllegalArgumentException("The job must be parsed with V2 skills selected, and with skills normalization enabled");
    }

    /**
     * Suggest professions based on the <b>skills</b> within a given job.
     * @param job The professions are suggested based on the <b>skills</b> within this job. Defaults limit returned to 10 and does not return missing skills. Use another overload to specify these parameters.
     * @return The API response body
     * @throws SovrenException Thrown when an API error occurs
     */
    public SuggestProfessionsResponse suggestProfessions(ParsedJob job) throws SovrenException {
        return suggestProfessions(job,10,false);
    }

    /**
     * Suggest professions based on a given set of skill IDs.
     * @param skillIds The skill IDs used to return the most relevant professions. The list can contain up to 50 skill IDs.
     * @param limit The maximum amount of professions returned. If not sure what value should be, provide 10 as default limit.
     * @param returnMissingSkills Flag to enable returning a list of missing skills per suggested profession.
     * @return The API response body
     * @throws SovrenException Thrown when an API error occurs
     */
    public SuggestProfessionsResponse suggestProfessions(List<String> skillIds, int limit, boolean returnMissingSkills) throws SovrenException {
        SuggestProfessionsRequest request = new SuggestProfessionsRequest();
        request.SkillIds = skillIds;
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

    /**
     * Suggest professions based on a given set of skill IDs.
     * @param skillIds The skill IDs used to return the most relevant professions. The list can contain up to 50 skill IDs. Defaults limit returned to 10 and does not return missing skills. Use another overload to specify these parameters.
     * @return The API response body
     * @throws SovrenException Thrown when an API error occurs
     */
    public SuggestProfessionsResponse suggestProfessions(List<String> skillIds) throws SovrenException {
        return suggestProfessions(skillIds,10,false);
    }
}