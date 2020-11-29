package com.sovren.models.api.matching.request;

import java.util.Optional;
import com.sovren.models.api.matching.CategoryWeights;
import com.sovren.models.api.matching.BaseScoredResponseValue;

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
     * </p><p>
     * See also: {@link BaseScoredResponseValue#AppliedCategoryWeights}
     * </p>
    */
    public CategoryWeights PreferredCategoryWeights;
}
