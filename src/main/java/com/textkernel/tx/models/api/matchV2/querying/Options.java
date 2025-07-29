// Copyright © 2023 Textkernel BV. All rights reserved.
// This file is provided for use by, or on behalf of, Textkernel licensees
// within the terms of their license of Textkernel products or Textkernel customers
// within the Terms of Service pertaining to the Textkernel SaaS products.

package com.textkernel.tx.models.api.matchV2.querying;

import java.util.List;

/** Options for a Search or Match request */
public class Options {
    /**
     * The roles associated with the request. Defaults to "All" if none are provided.
     */
    public List<String> Roles;

    /**
     * If true then no result list is returned. Used for example when only the cloud is needed.
     */
    public Boolean SupressResultList;
    /**
     * If true then spelling correction on the input query is skipped.
     */
    public Boolean SupressCorrection;

    /**
     * Optional. If true search responses will be highlighted. Note: If a snippet was requested from the searcher as part of the
     * result fields, the snippet can still contain highlighting even when false/null.
     */
    public Boolean Highlight;

    /**
     * Optional parameter to specify the number of result items (max 1500).
     * Only use a high number in case you need a list of results for use in follow-up actions.
     * Do not use a high number when presenting results in a user interface.
     * Therefore, the max is 100 when used in combination with pagination parameters ({@link #SearchAfter} or
     * {@link #ResultOffset}). When this is larger than 100, facet counts and search term highlighting
     * are not provided ({@link #FacetCounts} and {@link #Highlight} are implicitly set to false).
     * If not provided, the pre-configured page size of the searcher will be used.
     * This value is ignored for external searchers.
     */
    public Integer PageSize;

    /**
     * Used for pagination within Elastic Search Searcher (ignored for external searchers).
     * Represents the sort values of the last item from the previous page. Must contain exactly the
     * {@link SearchResult#SearchAfter} returned from the previous page query.
     */
    public String[] SearchAfter;

    /**
     * the result item offset used for pagination. For example, a value of 20 will skip the top 20 results and return the subsequent
     * results starting with result 21. This is ignored if {@link #SearchAfter} is set. {@link #ResultOffset} + {@link #PageSize}
     * cannot be more than 10000.
     */
    public Integer ResultOffset;

    /**
     * Optional List of language codes preferred by the user to filter synonyms by languages.
     */
    public String[] SynonymLanguages;

    /**
     * Optional. If true the content of a query part item of type TEXT or LONG_TEXT (its term plus synonyms)
     * that overlaps more than 75% with a previous query part of the same field and condition
     * will be moved into the synonyms of that previous one.
     */
    public Boolean MergeOverLappingSynonyms;

    /**
     * Optional setting for synonym expansion mode.
     */
    public SynonymExpansionMode SynonymExpansionMode;

    /**
     * Optional customization of the fields to be included in the results. If it consists of a single wildcard
     * field marked by "*" (star) then all available fields will be returned. If empty then the default result
     * field set will be returned. Remark: Reducing the returned fields this way, will not in general improve
     * performance, since Search is optimized to return the standard configured field set. It is not possible to
     * request system fields such as 'roles' or nested sub-fields.
     */
    public String[] ResultFields;

    /**
     * Optional. If true the search responses will contain facet count. May result in slower response times.
     */
    public Boolean FacetCounts;

    /**
     * Optional sorting definition.
     */
    public Sorting[] Sorting;

    /**
     * Optional flag indicating that the backend needs to use the Natural Language
     * Query Service (NLQS) to interpret the query String.
     */
    public Boolean UseNLQS;
}
