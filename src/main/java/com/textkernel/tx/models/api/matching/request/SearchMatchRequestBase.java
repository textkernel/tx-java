// Copyright © 2023 Textkernel BV. All rights reserved.
// This file is provided for use by, or on behalf of, Textkernel licensees
// within the terms of their license of Sovren products or Textkernel customers
// within the Terms of Service pertaining to the Textkernel SaaS products.

package com.textkernel.tx.models.api.matching.request;

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
