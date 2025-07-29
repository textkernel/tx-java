// Copyright © 2023 Textkernel BV. All rights reserved.
// This file is provided for use by, or on behalf of, Textkernel licensees
// within the terms of their license of Textkernel products or Textkernel customers
// within the Terms of Service pertaining to the Textkernel SaaS products.

package com.textkernel.tx.services;

import java.util.List;
import java.util.Map;

import com.textkernel.tx.EnvironmentSettings;
import com.textkernel.tx.TxClient;
import com.textkernel.tx.exceptions.TxException;
import com.textkernel.tx.http.HttpResponse;
import com.textkernel.tx.models.api.matchV2.autocomplete.AutocompleteCandidatesField;
import com.textkernel.tx.models.api.matchV2.autocomplete.AutocompleteJobsField;
import com.textkernel.tx.models.api.matchV2.autocomplete.AutocompleteRequest;
import com.textkernel.tx.models.api.matchV2.autocomplete.AutocompleteResponse;
import com.textkernel.tx.models.api.matchV2.documents.AddCandidateRequest;
import com.textkernel.tx.models.api.matchV2.documents.AddCandidateResponse;
import com.textkernel.tx.models.api.matchV2.documents.AddJobRequest;
import com.textkernel.tx.models.api.matchV2.documents.AddJobResponse;
import com.textkernel.tx.models.api.matchV2.documents.DeleteDocumentsResponse;
import com.textkernel.tx.models.api.matchV2.documents.DocumentSource;
import com.textkernel.tx.models.api.matchV2.querying.MatchRequest;
import com.textkernel.tx.models.api.matchV2.querying.Options;
import com.textkernel.tx.models.api.matchV2.querying.SearchQuery;
import com.textkernel.tx.models.api.matchV2.querying.SearchResponse;
import com.textkernel.tx.models.job.ParsedJob;
import com.textkernel.tx.models.resume.ParsedResume;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/** See {@link TxClient#searchMatchV2()} */
public class MatchV2Service extends ServiceBase {

    /** See {@link TxClient#searchMatchV2()} */
    public MatchV2Service(OkHttpClient httpClient, EnvironmentSettings settings) {
        super(httpClient, settings);
    }

    /**
     * Upload a candidates CV to the search and match V2 environment.
     * @param documentId The id to use for the document
     * @param resume Parsed output from the Textkernel CV/Resume Parser
     * @param roles (optional) The roles associated with the request. Defaults to <code>["All"]</code> if none are provided.
     * @param anonymize (optional) A boolean flag to strip PII data out of the resume before indexing
     * @param customFields (optional) A collection of custom fields represented as key-value pairs
     * @return The API response body
     * @throws TxException Thrown when an API error occurs
     */
    public AddCandidateResponse addCandidate(String documentId, ParsedResume resume, List<String> roles, boolean anonymize, Map<String, String> customFields) throws TxException {
        AddCandidateRequest request = new AddCandidateRequest();
        request.Anonymize = anonymize;
        request.ResumeData = resume;
        request.Roles = roles;
        request.SearchAndMatchEnvironment = _settings.MatchV2Environment;
        request.CustomFields = customFields;

        RequestBody body = createJsonBody(request);
        Request apiRequest = new Request.Builder()
            .url(_endpoints.matchV2CandidatesAddDocument(documentId))
            .post(body)
            .build();

        HttpResponse<AddCandidateResponse> response = executeRequest(apiRequest, AddCandidateResponse.class, getBodyIfDebug(apiRequest));
        return response.getData();
    }

    /**
     * Upload a job to the search and match V2 environment.
     * @param documentId The id to use for the document
     * @param resume Parsed output from the Textkernel Job Parser
     * @param roles (optional) The roles associated with the request. Defaults to <code>["All"]</code> if none are provided.
     * @param customFields (optional) A collection of custom fields represented as key-value pairs
     * @return The API response body
     * @throws TxException Thrown when an API error occurs
     */
    public AddJobResponse addJob(String documentId, ParsedJob job, List<String> roles, Map<String, String> customFields) throws TxException {
        AddJobRequest request = new AddJobRequest();
        request.JobData = job;
        request.Roles = roles;
        request.SearchAndMatchEnvironment = _settings.MatchV2Environment;
        request.CustomFields = customFields;

        RequestBody body = createJsonBody(request);
        Request apiRequest = new Request.Builder()
            .url(_endpoints.matchV2JobsAddDocument(documentId))
            .post(body)
            .build();

        HttpResponse<AddJobResponse> response = executeRequest(apiRequest, AddJobResponse.class, getBodyIfDebug(apiRequest));
        return response.getData();
    }

    /**
     * Delete candidate documents from environment
     * @param documentIds The document IDs to delete
     * @return The API response body
     * @throws TxException Thrown when an API error occurs
     */
    public DeleteDocumentsResponse deleteCandidates(List<String> documentIds) throws TxException {
        Request apiRequest = new Request.Builder()
            .url(_endpoints.matchV2CandidatesDeleteDocuments(documentIds, _settings.MatchV2Environment.toString()))
            .delete()
            .build();

        HttpResponse<DeleteDocumentsResponse> response = executeRequest(apiRequest, DeleteDocumentsResponse.class, getBodyIfDebug(apiRequest));
        return response.getData();
    }

    /**
     * Delete job documents from environment
     * @param documentIds The document IDs to delete
     * @return The API response body
     * @throws TxException Thrown when an API error occurs
     */
    public DeleteDocumentsResponse deleteJobs(List<String> documentIds) throws TxException {
        Request apiRequest = new Request.Builder()
            .url(_endpoints.matchV2JobsDeleteDocuments(documentIds, _settings.MatchV2Environment.toString()))
            .delete()
            .build();

        HttpResponse<DeleteDocumentsResponse> response = executeRequest(apiRequest, DeleteDocumentsResponse.class, getBodyIfDebug(apiRequest));
        return response.getData();
    }

    private SearchResponse matchInternal(Options options, SearchQuery query, DocumentSource sourceDocument, String url) throws TxException {
        MatchRequest request = new MatchRequest();
        request.Options = options;
        request.SearchAndMatchEnvironment = _settings.MatchV2Environment;
        request.Query = query;
        request.SourceDocument = sourceDocument;

        RequestBody body = createJsonBody(request);
        Request apiRequest = new Request.Builder()
            .url(url)
            .post(body)
            .build();

        HttpResponse<SearchResponse> response = executeRequest(apiRequest, SearchResponse.class, getBodyIfDebug(apiRequest));
        return response.getData();
    }

    private SearchResponse searchInternal(Options options, SearchQuery query, String url) throws TxException {
        return matchInternal(options, query, null, url);
    }

    /**
     * Match an existing candidate document with filters provided.
     * @param sourceDocument The document to generate the search query from
     * @param options Options for the Match request
     * @param query (optional) The query object that will be combined with the match query to drive the search
     * @return The API response body
     * @throws TxException Thrown when an API error occurs
     */
    public SearchResponse matchCandidates(DocumentSource sourceDocument, Options options, SearchQuery query) throws TxException {
        return matchInternal(options, query, sourceDocument, _endpoints.matchV2CandidatesMatchDocument());
    }

    /**
     * Match an existing job document with filters provided.
     * @param sourceDocument The document to generate the search query from
     * @param options Options for the Match request
     * @param query (optional) The query object that will be combined with the match query to drive the search
     * @return The API response body
     * @throws TxException Thrown when an API error occurs
     */
    public SearchResponse matchJobs(DocumentSource sourceDocument, Options options, SearchQuery query) throws TxException {
        return matchInternal(options, query, sourceDocument, _endpoints.matchV2JobsMatchDocument());
    }

    /**
     * Search for a candidate based on the query provided.
     * @param query The query object that will drive the search.
     * @param options Options for the search request
     * @return The API response body
     * @throws TxException Thrown when an API error occurs
     */
    public SearchResponse searchCandidates(SearchQuery query, Options options) throws TxException {
        return searchInternal(options, query, _endpoints.matchV2CandidatesSearch());
    }

    /**
     * Search for a job based on the query provided.
     * @param query The query object that will drive the search.
     * @param options Options for the search request
     * @return The API response body
     * @throws TxException Thrown when an API error occurs
     */
    public SearchResponse searchJobs(SearchQuery query, Options options) throws TxException {
        return searchInternal(options, query, _endpoints.matchV2JobsSearch());
    }

    private <T> AutocompleteResponse autocompleteInternal(String url, T field, String input, String... languages) throws TxException {
        AutocompleteRequest<T> request = new AutocompleteRequest<T>();
        request.Field = field;
        request.Input = input;
        request.SearchAndMatchEnvironment = _settings.MatchV2Environment;
        request.Language = String.join(",", languages);

        RequestBody body = createJsonBody(request);
        Request apiRequest = new Request.Builder()
            .url(url)
            .post(body)
            .build();

        HttpResponse<AutocompleteResponse> response = executeRequest(apiRequest, AutocompleteResponse.class, getBodyIfDebug(apiRequest));
        return response.getData();
    }

    /**
     * Returns a list of suggested Completions. This endpoint is used to give a user instant
     * feedback while typing a query. If the given field is the FULLTEXT field, the service
     * returns suggestions from all configured dictionaries that are not explicitly excluded from full-text suggestions.
     * @param field Which field to use to retrieve completions
     * @param input The user-typed input string
     * @param languages
     * Optional 2-letter ISO-639-1 language codes. The first language is used for field label translations.
     * All languages are used to retrieve completions when the environment doesn't have default languages set.
     * @return The API response body
     * @throws TxException Thrown when an API error occurs
     */
    public AutocompleteResponse autocompleteCandidates(AutocompleteCandidatesField field, String input, String... languages) throws TxException {
        return autocompleteInternal(_endpoints.matchV2CandidatesAutocomplete(), field, input, languages);
    }

    /**
     * Returns a list of suggested Completions. This endpoint is used to give a user instant
     * feedback while typing a query. If the given field is the FULLTEXT field, the service
     * returns suggestions from all configured dictionaries that are not explicitly excluded from full-text suggestions.
     * @param field Which field to use to retrieve completions
     * @param input The user-typed input string
     * @param languages
     * Optional 2-letter ISO-639-1 language codes. The first language is used for field label translations.
     * All languages are used to retrieve completions when the environment doesn't have default languages set.
     * @return The API response body
     * @throws TxException Thrown when an API error occurs
     */
    public AutocompleteResponse autocompleteJobs(AutocompleteJobsField field, String input, String... languages) throws TxException {
        return autocompleteInternal(_endpoints.matchV2JobsAutocomplete(), field, input, languages);
    }
}
