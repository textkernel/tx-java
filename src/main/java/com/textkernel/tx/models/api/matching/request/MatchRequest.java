// Copyright © 2020 Sovren Group, Inc. All rights reserved.
// This file is provided for use by, or on behalf of, Sovren licensees
// within the terms of their license of Sovren products or Sovren customers
// within the Terms of Service pertaining to the Sovren SaaS products.

package com.textkernel.tx.models.api.matching.request;

import com.textkernel.tx.models.api.matching.CategoryWeights;
import com.textkernel.tx.models.api.matching.BaseScoredResponseValue;

/**
* Request body for a Match request
*/
public class MatchRequest extends SearchMatchRequestBase {

    /** The number of results to return.*/
    public int Take;

    /**
     * The weights you want to use for scoring. <b>It is important to specify these, otherwise default values will be used. </b>
     * <p>These weights will be used except in the case
     * that you provided a non-zero weight for a category that is irrelevant in the source document.
     * For example, this can happen when the source document contains no languages.
     * <p>
     * See also: {@link BaseScoredResponseValue#AppliedCategoryWeights}
     * 
    */
    public CategoryWeights PreferredCategoryWeights;
}
