// Copyright © 2020 Sovren Group, Inc. All rights reserved.
// This file is provided for use by, or on behalf of, Sovren licensees
// within the terms of their license of Sovren products or Sovren customers
// within the Terms of Service pertaining to the Sovren SaaS products.

package com.textkernel.tx.models.api.matching.response;

/**
* Contains information about why the score is a certain value
*/
public class CategoryScoreEvidence {
    
    /** Information regarding the outcome of one or more of the data points in the query.*/
    public String Fact;
    
    /**
     * The sentiment of the {@link #Fact}. This also indicates if this evidence led to a higher or lower score. One of:
     * <ul>
     * <li>Negative</li>
     * <li>Positive</li>
     * <li>Mixed</li>
     * </ul>
    */
    public String Type;
}
