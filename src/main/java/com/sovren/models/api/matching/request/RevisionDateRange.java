// Copyright © 2020 Sovren Group, Inc. All rights reserved.
// This file is provided for use by, or on behalf of, Sovren licensees
// within the terms of their license of Sovren products or Sovren customers
// within the Terms of Service pertaining to the Sovren SaaS products.

package com.sovren.models.api.matching.request;

/**
* A range of revision dates in ISO 8601 (yyyy-MM-dd) format
*/
public class RevisionDateRange {
    
    /** the minimum (oldest) date in ISO 8601 (yyyy-MM-dd) format*/
    public String Minimum;
    
    /** the maximum (most recent) date in ISO 8601 (yyyy-MM-dd) format*/
    public String Maximum;
}
