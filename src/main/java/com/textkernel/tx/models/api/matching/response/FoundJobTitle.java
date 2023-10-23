// Copyright © 2023 Textkernel BV. All rights reserved.
// This file is provided for use by, or on behalf of, Textkernel licensees
// within the terms of their license of Sovren products or Textkernel customers
// within the Terms of Service pertaining to the Textkernel SaaS products.

package com.textkernel.tx.models.api.matching.response;

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
