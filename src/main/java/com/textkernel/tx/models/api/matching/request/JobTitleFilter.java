// Copyright © 2020 Sovren Group, Inc. All rights reserved.
// This file is provided for use by, or on behalf of, Sovren licensees
// within the terms of their license of Sovren products or Sovren customers
// within the Terms of Service pertaining to the Sovren SaaS products.

package com.textkernel.tx.models.api.matching.request;

/**
* Criteria for filtering on a specific job title
*/
public class JobTitleFilter {

    /**
     * The name of the Job Title. Supports (*, ?) wildcard characters after the third character
     * in the term as defined in https://sovren.com/technical-specs/latest/rest-api/ai-matching/overview/querying/#ai-filtering-special-operators
    */
    public String Title;

    /** Whether or not the job title must be a current job title*/
    public boolean IsCurrent;
}
