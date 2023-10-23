// Copyright © 2020 Sovren Group, Inc. All rights reserved.
// This file is provided for use by, or on behalf of, Sovren licensees
// within the terms of their license of Sovren products or Sovren customers
// within the Terms of Service pertaining to the Sovren SaaS products.

package com.textkernel.tx.models.api.matching;

import com.textkernel.tx.models.api.bimetricscoring.BimetricScoreRequest;
import com.textkernel.tx.models.api.matching.request.MatchRequest;

/**
* A base class for all scored responses
*/
public class BaseScoredResponseValue<T> extends BaseSearchMatchResponseValue<T> {

    /**
     * The weights suggested by Sovren based solely on the data in the source document.
     * <p>NOTE: these should only be used as a fallback or initial value. Your system/users
     * should have the ability to adjust/override these (in the PreferredCategoryWeights in the request)
     * 
     * <p>
     * See also: <ul>
     * <li>{@link BimetricScoreRequest#PreferredCategoryWeights}</li>
     * <li>{@link MatchRequest#PreferredCategoryWeights}</li>
     * </ul>
    */
    public CategoryWeights SuggestedCategoryWeights;
    
    /**
     * The weights that were actually used for scoring. These are either:
     * <ol>
     * <li>if the PreferredCategoryWeights are specified in the request, these are used (with any adjustments for non-applicable categories)</li>
     * <li>otherwise these are simply the {@link #SuggestedCategoryWeights}</li>
     * </ol>
     * <p>
     * See also: <ul>
     * <li>{@link BimetricScoreRequest#PreferredCategoryWeights}</li>
     * <li>{@link MatchRequest#PreferredCategoryWeights}</li>
     * </ul>
    */
    public CategoryWeights AppliedCategoryWeights;
}
