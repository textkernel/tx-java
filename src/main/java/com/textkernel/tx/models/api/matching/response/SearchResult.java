// Copyright © 2020 Sovren Group, Inc. All rights reserved.
// This file is provided for use by, or on behalf of, Sovren licensees
// within the terms of their license of Sovren products or Sovren customers
// within the Terms of Service pertaining to the Sovren SaaS products.

package com.textkernel.tx.models.api.matching.response;

/**
* A single result from a search query
*/
public class SearchResult {
    
    /** The document id of the search/match result*/
    public String Id;
    
    /** The id of the index containing the document*/
    public String IndexId;
}
