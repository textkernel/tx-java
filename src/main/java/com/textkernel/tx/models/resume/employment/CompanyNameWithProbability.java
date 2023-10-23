// Copyright © 2020 Sovren Group, Inc. All rights reserved.
// This file is provided for use by, or on behalf of, Sovren licensees
// within the terms of their license of Sovren products or Sovren customers
// within the Terms of Service pertaining to the Sovren SaaS products.

package com.textkernel.tx.models.resume.employment;

import com.textkernel.tx.models.resume.NormalizedString;

/**
* A company name that has been normalized and assigned a probability
*/
public class CompanyNameWithProbability extends NormalizedString {

    /**
     * The degree of certainty that the company name is accurate. One of:
     * <ul>
     * <li>VeryUnlikely - recommend discarding</li>
     * <li>Unlikely - recommend discarding</li>
     * <li>Probable - recommend review</li>
     * <li>Confident - no action needed</li>
     * </ul>
    */
    public String Probability;
}
