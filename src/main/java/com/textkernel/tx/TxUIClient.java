// Copyright © 2023 Textkernel BV. All rights reserved.
// This file is provided for use by, or on behalf of, Textkernel licensees
// within the terms of their license of Textkernel products or Textkernel customers
// within the Terms of Service pertaining to the Textkernel SaaS products.

package com.textkernel.tx;

import com.textkernel.tx.exceptions.TxException;
import com.textkernel.tx.models.api.bimetricscoring.*;
import com.textkernel.tx.models.api.matching.*;
import com.textkernel.tx.models.api.matching.request.FilterCriteria;
import com.textkernel.tx.models.api.matching.request.MatchByDocumentIdOptions;
import com.textkernel.tx.models.api.matching.request.PaginationSettings;
import com.textkernel.tx.models.api.matching.request.SearchMatchSettings;
import com.textkernel.tx.models.api.matching.ui.GenerateUIResponse;
import com.textkernel.tx.models.api.matching.ui.request.*;
import com.textkernel.tx.models.job.ParsedJob;
import com.textkernel.tx.models.resume.ParsedResume;

import java.util.List;

/** Extension methods to generate the Matching UI. Access with {@link TxClient#ui(MatchUISettings)}*/
public class TxUIClient {

    private final MatchUISettings _uiSessionOptions;
    private final TxClient _client;

    TxUIClient(MatchUISettings uiOptions, TxClient client) {
        _uiSessionOptions = uiOptions;
        _client = client;
    }

    /**
     * Create a Matching UI session to find matches for a non-indexed resume.
     * @param resume The resume (generated by the Resume Parser) to use as the source for a match query
     * @param indexesToQuery The indexes to find results in. These must all be of the same type (resumes or jobs)
     * @param preferredWeights
     * The preferred category weights for scoring the results. If {@code null},
     * The best values will be determined based on the source resume
     * @param filters Any filters to apply prior to the match (a result must satisfy all the filters), or {@code null}
     * @param settings The settings for this match request. Use {@code null} for defaults.
     * @param numResults The number of results to show. Use {@code 0} for the default.
     * @return The response containing a URL for the Matching UI session
     * @throws TxException Thrown when an API error occurs
     */
    public GenerateUIResponse match(
            ParsedResume resume,
            List<String> indexesToQuery,
            CategoryWeights preferredWeights,
            FilterCriteria filters,
            SearchMatchSettings settings,
            int numResults) throws TxException {

        MatchResumeRequest request = _client.createRequest(resume, indexesToQuery, preferredWeights, filters, settings, numResults);
        UIMatchResumeRequest uiRequest = new UIMatchResumeRequest(request, _uiSessionOptions);
        return _client.uiMatch(uiRequest);
    }

    /**
     * Create a Matching UI session to find matches for a non-indexed job.
     * @param job The job (generated by the Job Parser) to use as the source for a match query
     * @param indexesToQuery The indexes to find results in. These must all be of the same type (resumes or jobs)
     * @param preferredWeights
     * The preferred category weights for scoring the results. If {@code null},
     * The best values will be determined based on the source job
     * @param filters Any filters to apply prior to the match (a result must satisfy all the filters), or {@code null}
     * @param settings The settings for this match request. Use {@code null} for defaults.
     * @param numResults The number of results to show. Use {@code 0} for the default.
     * @return The response containing a URL for the Matching UI session
     * @throws TxException Thrown when an API error occurs
     */
    public GenerateUIResponse match(
            ParsedJob job,
            List<String> indexesToQuery,
            CategoryWeights preferredWeights,
            FilterCriteria filters,
            SearchMatchSettings settings,
            int numResults) throws TxException {

        MatchJobRequest request = _client.createRequest(job, indexesToQuery, preferredWeights, filters, settings, numResults);
        UIMatchJobRequest uiRequest = new UIMatchJobRequest(request, _uiSessionOptions);
        return _client.uiMatch(uiRequest);
    }

    /**
     * Create a Matching UI session to find matches for a resume or job that is already indexed
     * @param indexId The index containing the document you want to match
     * @param documentId The ID of the document to match
     * @param indexesToQuery The indexes to find results in. These must all be of the same type (resumes or jobs)
     * @param preferredWeights
     * The preferred category weights for scoring the results. If {@code null},
     * The best values will be determined based on the source resume/job
     * @param filters Any filters to apply prior to the match (a result must satisfy all the filters), or {@code null}
     * @param settings The settings for this match request. Use {@code null} for defaults.
     * @param numResults The number of results to show. Use {@code 0} for the default.
     * @return The response containing a URL for the Matching UI session
     * @throws TxException Thrown when an API error occurs
     */
    public GenerateUIResponse match(
            String indexId,
            String documentId,
            List<String> indexesToQuery,
            CategoryWeights preferredWeights,
            FilterCriteria filters,
            SearchMatchSettings settings,
            int numResults) throws TxException {

        MatchByDocumentIdOptions request = _client.createRequest(indexesToQuery, preferredWeights, filters, settings, numResults);
        UIMatchByDocumentIdOptions uiRequest = new UIMatchByDocumentIdOptions(request, _uiSessionOptions);
        return _client.uiMatch(indexId, documentId, uiRequest);
    }

    /**
     * Create a Matching UI session to search for resumes or jobs that meet specific criteria
     * @param indexesToQuery The indexes to find results in. These must all be of the same type (resumes or jobs)
     * @param query The search query. A result must satisfy all of these criteria
     * @param settings The settings for this search request. Use {@code null} for defaults.
     * @param pagination Pagination settings. Use {@code null} for defaults.
     * @return The response containing a URL for the Matching UI session
     * @throws TxException Thrown when an API error occurs
     */
    public GenerateUIResponse search(
            List<String> indexesToQuery,
            FilterCriteria query,
            SearchMatchSettings settings,
            PaginationSettings pagination) throws TxException {

        SearchRequest request = _client.createRequest(indexesToQuery, query, settings, pagination);
        UISearchRequest uiRequest = new UISearchRequest(request, _uiSessionOptions);
        return _client.uiSearch(uiRequest);
    }

    /**
     * Create a Matching UI session to score one or more target documents against a source resume
     * @param <TTarget> Either {@link ParsedResumeWithId} or {@link ParsedJobWithId}
     * @param sourceResume The source resume
     * @param targetDocuments The target resumes/jobs
     * @param preferredWeights
     * The preferred category weights for scoring the results. If {@code null},
     * The best values will be determined based on the source resume.
     * @param settings The settings for this search request. Use {@code null} for defaults.
     * @return The response containing a URL for the Matching UI session
     * @throws TxException Thrown when an API error occurs
     */
    public <TTarget extends IParsedDocWithId> GenerateUIResponse bimetricScore(
            ParsedResumeWithId sourceResume,
            List<TTarget> targetDocuments,
            CategoryWeights preferredWeights,
            SearchMatchSettings settings) throws TxException {
        BimetricScoreResumeRequest request = _client.createRequest(sourceResume, targetDocuments, preferredWeights, settings);
        UIBimetricScoreResumeRequest uiRequest = new UIBimetricScoreResumeRequest(request, _uiSessionOptions);
        return _client.uiBimetricScore(uiRequest);
    }

    /**
     * Create a Matching UI session to score one or more target documents against a source job
     * @param <TTarget> Either {@link ParsedResumeWithId} or {@link ParsedJobWithId}
     * @param sourceJob The source job
     * @param targetDocuments The target resumes/jobs
     * @param preferredWeights
     * The preferred category weights for scoring the results. If {@code null},
     * The best values will be determined based on the source job.
     * @param settings The settings for this search request. Use {@code null} for defaults.
     * @return The response containing a URL for the Matching UI session
     * @throws TxException Thrown when an API error occurs
     */
    public <TTarget extends IParsedDocWithId> GenerateUIResponse bimetricScore(
            ParsedJobWithId sourceJob,
            List<TTarget> targetDocuments,
            CategoryWeights preferredWeights,
            SearchMatchSettings settings) throws TxException {
        BimetricScoreJobRequest request = _client.createRequest(sourceJob, targetDocuments, preferredWeights, settings);
        UIBimetricScoreJobRequest uiRequest = new UIBimetricScoreJobRequest(request, _uiSessionOptions);
        return _client.uiBimetricScore(uiRequest);
    }
}
