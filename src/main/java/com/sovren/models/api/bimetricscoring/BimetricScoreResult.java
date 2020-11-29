package com.sovren.models.api.bimetricscoring;

import com.sovren.models.api.matching.BaseScoredResponseValue;
import com.sovren.models.api.matching.response.CategoryScoreData;
import com.sovren.models.api.matching.response.EnrichedScoreData;

/**
* And individual result (representing a single document) for a 'BimetricScore' request
*/
public class BimetricScoreResult {
    
    /** The document id of the result*/
    public String Id;
    
    /**
     * An integer score representing the overall fit of the match.
     * This is the result of a proprietary algorithm that combines the
     * {@link #WeightedScore} and the {@link #ReverseCompatibilityScore} 
     * into one overall score. Results are sorted by this parameter in descending order.
    */
    public int SovScore;
    
    /**
     * An integer score from 0-100 representing how well the current document matched the source document. 
     * This calculation is the sum of the unweighted category scores multiplied by their respective applied weight.
     * A score of 100 means that all of the data points in the source document were found in the target document, 
     * but the target document may have had many extra data points.
     * <p> See also:<ul>
     * <li>{@link CategoryScoreData#UnweightedScore}</li>
     * <li> {@link BaseScoredResponseValue#AppliedCategoryWeights}</li>
     * </ul>
    */
    public int WeightedScore;
    
    /**
     * An integer score from 0-100 which represents how well the target document matched to the source document.
     * This is equivalent to the {@link #WeightedScore} if you ran the match/score with the source and 
     * target documents swapped. A score of 100 means that all of the data points in the target document were found
     * in the source document, but the source document may have had many extra data points.
    */
    public int ReverseCompatibilityScore;
    
    /** Detailed information/evidence about the {@link #WeightedScore}*/
    public EnrichedScoreData EnrichedScoreData;
    
    /** Detailed information/evidence about the {@link #ReverseCompatibilityScore} */
    public EnrichedScoreData EnrichedRCSScoreData;
}
