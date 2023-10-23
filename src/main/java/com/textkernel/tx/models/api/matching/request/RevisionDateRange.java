// Copyright © 2023 Textkernel BV. All rights reserved.
// This file is provided for use by, or on behalf of, Textkernel licensees
// within the terms of their license of Textkernel products or Textkernel customers
// within the Terms of Service pertaining to the Textkernel SaaS products.

package com.textkernel.tx.models.api.matching.request;

/**
* A range of revision dates in ISO 8601 (yyyy-MM-dd) format
*/
public class RevisionDateRange {
    
    /** the minimum (oldest) date in ISO 8601 (yyyy-MM-dd) format*/
    public String Minimum;
    
    /** the maximum (most recent) date in ISO 8601 (yyyy-MM-dd) format*/
    public String Maximum;
}
