// Copyright © 2023 Textkernel BV. All rights reserved.
// This file is provided for use by, or on behalf of, Textkernel licensees
// within the terms of their license of Textkernel products or Textkernel customers
// within the Terms of Service pertaining to the Textkernel SaaS products.

package com.textkernel.tx.models.api.matchV2.querying.results;

public class SearchResult {
    public long MatchSize;
    public boolean HasMoreResults;
    public ResultItem[] ResultItems;
    public QueryPart[] QueryParts;
    public QueryPart[] NewQueryParts;
    public boolean IsOrCombined;
    public String TransformedQuery;
    public Facet[] FacetCounts;
    public String SearchEngine;
    public SearchResultEntry[] Synonyms;
    public QueryContext QueryContext;
    public String[] SearchAfter;
    public long EsTimeMs;
    public boolean EsTimeMsSpecified;
    public String[] Warning;
}