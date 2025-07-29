// Copyright © 2023 Textkernel BV. All rights reserved.
// This file is provided for use by, or on behalf of, Textkernel licensees
// within the terms of their license of Textkernel products or Textkernel customers
// within the Terms of Service pertaining to the Textkernel SaaS products.

package com.textkernel.tx.services;

import java.util.List;

import com.textkernel.tx.EnvironmentSettings;
import com.textkernel.tx.TxClient;
import com.textkernel.tx.exceptions.TxException;
import com.textkernel.tx.http.HttpResponse;
import com.textkernel.tx.models.api.bimetricscoring.BimetricScoreJobRequest;
import com.textkernel.tx.models.api.bimetricscoring.BimetricScoreResponse;
import com.textkernel.tx.models.api.bimetricscoring.BimetricScoreResumeRequest;
import com.textkernel.tx.models.api.bimetricscoring.IParsedDocWithId;
import com.textkernel.tx.models.api.bimetricscoring.ParsedJobWithId;
import com.textkernel.tx.models.api.bimetricscoring.ParsedResumeWithId;
import com.textkernel.tx.models.api.indexes.CreateIndexRequest;
import com.textkernel.tx.models.api.indexes.CreateIndexResponse;
import com.textkernel.tx.models.api.indexes.DeleteDocumentResponse;
import com.textkernel.tx.models.api.indexes.DeleteIndexResponse;
import com.textkernel.tx.models.api.indexes.DeleteMultipleDocumentsRequest;
import com.textkernel.tx.models.api.indexes.DeleteMultipleDocumentsResponse;
import com.textkernel.tx.models.api.indexes.GetAllIndexesResponse;
import com.textkernel.tx.models.api.indexes.GetJobResponse;
import com.textkernel.tx.models.api.indexes.GetResumeResponse;
import com.textkernel.tx.models.api.indexes.IndexDocumentResponse;
import com.textkernel.tx.models.api.indexes.IndexJobInfo;
import com.textkernel.tx.models.api.indexes.IndexJobRequest;
import com.textkernel.tx.models.api.indexes.IndexMultipleDocumentsResponse;
import com.textkernel.tx.models.api.indexes.IndexMultipleJobsRequest;
import com.textkernel.tx.models.api.indexes.IndexMultipleResumesRequest;
import com.textkernel.tx.models.api.indexes.IndexResumeInfo;
import com.textkernel.tx.models.api.indexes.IndexResumeRequest;
import com.textkernel.tx.models.api.indexes.UpdateUserDefinedTagsRequest;
import com.textkernel.tx.models.api.indexes.UpdateUserDefinedTagsResponse;
import com.textkernel.tx.models.api.indexes.UserDefinedTagsMethod;
import com.textkernel.tx.models.api.matching.CategoryWeights;
import com.textkernel.tx.models.api.matching.MatchJobRequest;
import com.textkernel.tx.models.api.matching.MatchResponse;
import com.textkernel.tx.models.api.matching.MatchResumeRequest;
import com.textkernel.tx.models.api.matching.SearchRequest;
import com.textkernel.tx.models.api.matching.SearchResponse;
import com.textkernel.tx.models.api.matching.request.FilterCriteria;
import com.textkernel.tx.models.api.matching.request.MatchByDocumentIdOptions;
import com.textkernel.tx.models.api.matching.request.PaginationSettings;
import com.textkernel.tx.models.api.matching.request.SearchMatchSettings;
import com.textkernel.tx.models.job.ParsedJob;
import com.textkernel.tx.models.matching.IndexType;
import com.textkernel.tx.models.resume.ParsedResume;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/** See {@link TxClient#searchMatchV1()} */
public class SearchMatchService extends ServiceBase {

    /**
     * Do not use this. See {@link TxClient#searchMatchV1()}
     * @param httpClient The http client for API calls
     * @param settings environment settings
     */
    public SearchMatchService(OkHttpClient httpClient, EnvironmentSettings settings) {
        super(httpClient, settings);
    }

    /**
     * Create a new index
     * @param type The type of documents stored in this index. Either 'Resume' or 'Job'
     * @param indexId
     * The ID to assign to the new index. This is restricted to alphanumeric with dashes 
     * and underscores. All values will be converted to lower-case.
     * @return The API response body
     * @throws TxException Thrown when an API error occurs
     */
    public CreateIndexResponse createIndex(IndexType type, String indexId) throws TxException {
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
     * @throws TxException Thrown when an API error occurs
     */
    public GetAllIndexesResponse getAllIndexes() throws TxException {
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
     * @throws TxException Thrown when an API error occurs
     */
    public DeleteIndexResponse deleteIndex(String indexId) throws TxException {
        Request apiRequest = new Request.Builder()
            .url(_endpoints.index(indexId))
            .delete()
            .build();
        
        HttpResponse<DeleteIndexResponse> response = executeRequest(apiRequest, DeleteIndexResponse.class, getBodyIfDebug(apiRequest));
        return response.getData();
    }

    /**
     * Add a resume to an existing index
     * @param resume A resume generated by the Resume Parser
     * @param indexId The index the document should be added into (case-insensitive).
     * @param documentId
     * The ID to assign to the new document. This is restricted to alphanumeric 
     * with dashes and underscores. All values will be converted to lower-case.
     * @param userDefinedTags The user-defined tags that the resume should have, or {@code null}
     * @return The API response body
     * @throws TxException Thrown when an API error occurs
     */
    public IndexDocumentResponse indexDocument(
            ParsedResume resume,
            String indexId,
            String documentId,
            List<String> userDefinedTags) throws TxException {
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
     * @param job A job generated by the Job Parser
     * @param indexId The index the document should be added into (case-insensitive).
     * @param documentId
     * The ID to assign to the new document. This is restricted to alphanumeric
     * with dashes and underscores. All values will be converted to lower-case.
     * @param userDefinedTags The user-defined tags that the resume should have, or {@code null}
     * @return The API response body
     * @throws TxException Thrown when an API error occurs
     */
    public IndexDocumentResponse indexDocument(
            ParsedJob job,
            String indexId,
            String documentId,
            List<String> userDefinedTags) throws TxException {
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
     * @param resumes The resumes generated by the Resume Parser paired with their DocumentIds
     * @param indexId The index the documents should be added into (case-insensitive).
     * @return The API response body
     * @throws TxException Thrown when an API error occurs
     */
    public IndexMultipleDocumentsResponse indexMultipleResumes(List<IndexResumeInfo> resumes, String indexId) throws TxException {
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
     * @param jobs The jobs generated by the Job Parser paired with their DocumentIds
     * @param indexId The index the documents should be added into (case-insensitive).
     * @return The API response body
     * @throws TxException Thrown when an API error occurs
     */
    public IndexMultipleDocumentsResponse indexMultipleJobs(List<IndexJobInfo> jobs, String indexId) throws TxException {
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
     * @throws TxException Thrown when an API error occurs
     */
    public DeleteDocumentResponse deleteDocument(String indexId, String documentId) throws TxException {
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
     * @throws TxException Thrown when an API error occurs
     */
    public DeleteMultipleDocumentsResponse deleteMultipleDocuments(String indexId, List<String> documentIds) throws TxException {
        DeleteMultipleDocumentsRequest request = new DeleteMultipleDocumentsRequest();
        request.DocumentIds = documentIds;
        RequestBody requestBody = createJsonBody(request);
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
     * @throws TxException Thrown when an API error occurs
     */
    public GetResumeResponse getResume(String indexId, String documentId) throws TxException {
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
     * @throws TxException Thrown when an API error occurs
     */
    public GetJobResponse getJob(String indexId, String documentId) throws TxException {
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
     * @throws TxException Thrown when an API error occurs
     */
    public UpdateUserDefinedTagsResponse updateResumeUserDefinedTags(
            String indexId,
            String documentId,
            List<String> userDefinedTags,
            UserDefinedTagsMethod method) throws TxException {
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
     * @throws TxException Thrown when an API error occurs
     */
    public UpdateUserDefinedTagsResponse updateJobUserDefinedTags(
            String indexId,
            String documentId,
            List<String> userDefinedTags,
            UserDefinedTagsMethod method) throws TxException {
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
     * @param resume The resume (generated by the Resume Parser) to use as the source for a match query
     * @param indexesToQuery The indexes to find results in. These must all be of the same type (resumes or jobs)
     * @param preferredWeights
     * The preferred category weights for scoring the results. If {@code null},
     * The best values will be determined based on the source resume
     * @param filters Any filters to apply prior to the match (a result must satisfy all the filters), or {@code null}
     * @param settings The settings for this match request. Use {@code null} for defaults.
     * @param numResults The number of results to show. Use {@code 0} for the default.
     * @return The API response body
     * @throws TxException Thrown when an API error occurs
     */
    public MatchResponse match(
            ParsedResume resume,
            List<String> indexesToQuery,
            CategoryWeights preferredWeights,
            FilterCriteria filters,
            SearchMatchSettings settings,
            int numResults) throws TxException {

        MatchResumeRequest request = createRequest(resume, indexesToQuery, preferredWeights, filters, settings, numResults);
        RequestBody body = createJsonBody(request);
        Request apiRequest = new Request.Builder()
                .url(_endpoints.matchResume())
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
     * @param job The job (generated by the Job Parser) to use as the source for a match query
     * @param indexesToQuery The indexes to find results in. These must all be of the same type (resumes or jobs)
     * @param preferredWeights
     * The preferred category weights for scoring the results. If {@code null},
     * The best values will be determined based on the source job
     * @param filters Any filters to apply prior to the match (a result must satisfy all the filters), or {@code null}
     * @param settings The settings for this match request. Use {@code null} for defaults.
     * @param numResults The number of results to show. Use {@code 0} for the default.
     * @return The API response body
     * @throws TxException Thrown when an API error occurs
     */
    public MatchResponse match(
            ParsedJob job,
            List<String> indexesToQuery,
            CategoryWeights preferredWeights,
            FilterCriteria filters,
            SearchMatchSettings settings,
            int numResults) throws TxException {

        MatchJobRequest request = createRequest(job, indexesToQuery, preferredWeights, filters, settings, numResults);
        RequestBody body = createJsonBody(request);
        Request apiRequest = new Request.Builder()
                .url(_endpoints.matchJob())
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
     * The best values will be determined based on the source resume/job
     * @param filters Any filters to apply prior to the match (a result must satisfy all the filters), or {@code null}
     * @param settings The settings for this match request. Use {@code null} for defaults.
     * @param numResults The number of results to show. Use {@code 0} for the default.
     * @return The API response body
     * @throws TxException Thrown when an API error occurs
     */
    public MatchResponse match(
            String indexId,
            String documentId,
            List<String> indexesToQuery,
            CategoryWeights preferredWeights,
            FilterCriteria filters,
            SearchMatchSettings settings,
            int numResults) throws TxException {

        MatchByDocumentIdOptions request = createRequest(indexesToQuery, preferredWeights, filters, settings, numResults);
        RequestBody body = createJsonBody(request);
        Request apiRequest = new Request.Builder()
                .url(_endpoints.matchDocId(indexId, documentId))
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

    /**
     * Search for resumes or jobs that meet specific criteria
     * @param indexesToQuery The indexes to find results in. These must all be of the same type (resumes or jobs)
     * @param query The search query. A result must satisfy all of these criteria
     * @param settings The settings for this search request. Use {@code null} for defaults.
     * @param pagination Pagination settings. Use {@code null} for defaults.
     * @return The API response body
     * @throws TxException Thrown when an API error occurs
     */
    public SearchResponse search(
            List<String> indexesToQuery,
            FilterCriteria query,
            SearchMatchSettings settings,
            PaginationSettings pagination) throws TxException {
        SearchRequest request = createRequest(indexesToQuery, query, settings, pagination);
        RequestBody body = createJsonBody(request);
        Request apiRequest = new Request.Builder()
                .url(_endpoints.search())
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

    /**
     * Score one or more target documents against a source resume
     * @param <TTarget> Either {@link ParsedResumeWithId} or {@link ParsedJobWithId}
     * @param sourceResume The source resume
     * @param targetDocuments The target resumes/jobs
     * @param preferredWeights
     * The preferred category weights for scoring the results. If {@code null},
     * The best values will be determined based on the source resume.
     * @param settings The settings for this search request. Use {@code null} for defaults.
     * @return The API response body
     * @throws TxException Thrown when an API error occurs
     */
    @SuppressWarnings("unchecked") //these actually are checked, compiler just can't tell
    public <TTarget extends IParsedDocWithId> BimetricScoreResponse bimetricScore(
            ParsedResumeWithId sourceResume,
            List<TTarget> targetDocuments,
            CategoryWeights preferredWeights,
            SearchMatchSettings settings) throws TxException {
        
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
        
        RequestBody body = createJsonBody(request);
        Request apiRequest = new Request.Builder()
                .url(_endpoints.bimetricScoreResume())
                .post(body)
                .build();

        HttpResponse<BimetricScoreResponse> response = executeRequest(apiRequest, BimetricScoreResponse.class, getBodyIfDebug(apiRequest));
        return response.getData();
    }

    /**
     * Score one or more target documents against a source job
     * @param <TTarget> Either {@link ParsedResumeWithId} or {@link ParsedJobWithId}
     * @param sourceJob The source job
     * @param targetDocuments The target resumes/jobs
     * @param preferredWeights
     * The preferred category weights for scoring the results. If {@code null},
     * The best values will be determined based on the source job.
     * @param settings The settings for this search request. Use {@code null} for defaults.
     * @return The API response body
     * @throws TxException Thrown when an API error occurs
     */
    @SuppressWarnings("unchecked") //these actually are checked, compiler just can't tell
    public <TTarget extends IParsedDocWithId> BimetricScoreResponse bimetricScore(
            ParsedJobWithId sourceJob,
            List<TTarget> targetDocuments,
            CategoryWeights preferredWeights,
            SearchMatchSettings settings) throws TxException {
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
        RequestBody body = createJsonBody(request);
        Request apiRequest = new Request.Builder()
                .url(_endpoints.bimetricScoreJob())
                .post(body)
                .build();

        HttpResponse<BimetricScoreResponse> response = executeRequest(apiRequest, BimetricScoreResponse.class, getBodyIfDebug(apiRequest));
        return response.getData();
    }
}
