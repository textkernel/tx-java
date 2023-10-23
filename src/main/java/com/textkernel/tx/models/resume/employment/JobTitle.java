// Copyright © 2020 Sovren Group, Inc. All rights reserved.
// This file is provided for use by, or on behalf of, Sovren licensees
// within the terms of their license of Sovren products or Sovren customers
// within the Terms of Service pertaining to the Sovren SaaS products.

package com.textkernel.tx.models.resume.employment;

import java.util.List;

/**
* A job title found in a resume
*/
public class JobTitle {
    
    /** The raw text as it was found in the resume*/
    public String Raw;
    
    /** 
     * The normalized job title
     * @deprecated use {@link Position#NormalizedProfession} instead
    */
    @Deprecated
    public String Normalized;
    
    /**
     * The degree of certainty that the job title value is accurate. One of:
     * <ul>
     * <li>VeryUnlikely - recommend discarding</li>
     * <li>Unlikely - recommend discarding</li>
     * <li>Probable - recommend review</li>
     * <li>Confident - no action needed</li>
     * </ul>
    */
    public String Probability;
    
    /** 
     * Any variations of this job title that might be useful for matching
     * @deprecated use {@link Position#NormalizedProfession} instead
    */
    @Deprecated
    public List<String> Variations;
}
