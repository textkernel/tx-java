// Copyright © 2020 Sovren Group, Inc. All rights reserved.
// This file is provided for use by, or on behalf of, Sovren licensees
// within the terms of their license of Sovren products or Sovren customers
// within the Terms of Service pertaining to the Sovren SaaS products.

package com.sovren.models.api.matching.request;

import java.util.List;

/**
* Base class for match/search requests
*/
public abstract class SearchMatchRequestBase {
    
    /** The ids of the indexes in which you want to find results (case-insensitive).*/
    public List<String> IndexIdsToSearchInto;
    
    /** The settings to use during searching/matching queries*/
    public SearchMatchSettings Settings;
    
    /** Required criteria for the result set.*/
    public FilterCriteria FilterCriteria;
}
