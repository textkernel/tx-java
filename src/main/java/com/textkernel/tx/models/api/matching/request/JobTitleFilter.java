// Copyright © 2023 Textkernel BV. All rights reserved.
// This file is provided for use by, or on behalf of, Textkernel licensees
// within the terms of their license of Textkernel products or Textkernel customers
// within the Terms of Service pertaining to the Textkernel SaaS products.

package com.textkernel.tx.models.api.matching.request;

/**
* Criteria for filtering on a specific job title
*/
public class JobTitleFilter {

    /**
     * The name of the Job Title. Supports (*, ?) wildcard characters after the third character
     * in the term as defined in https://developer.textkernel.com/Sovren/v10/ai-matching/overview/querying/#ai-filtering-special-operators
    */
    public String Title;

    /** Whether or not the job title must be a current job title*/
    public boolean IsCurrent;
}
