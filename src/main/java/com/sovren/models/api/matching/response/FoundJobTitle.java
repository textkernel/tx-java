// Copyright © 2020 Sovren Group, Inc. All rights reserved.
// This file is provided for use by, or on behalf of, Sovren licensees
// within the terms of their license of Sovren products or Sovren customers
// within the Terms of Service pertaining to the Sovren SaaS products.

package com.sovren.models.api.matching.response;

/**
* Information about a job title match
*/
public class FoundJobTitle {
    
    /** Exact term found.*/
    public String RawTerm;
    
    /** Original term that the variation was derived from (or {@code null} if the {@link #RawTerm} was an exact match)*/
    public String VariationOf;
    
    /** {@code true} when the job title found is in the current time-frame.*/
    public boolean IsCurrent;
}
