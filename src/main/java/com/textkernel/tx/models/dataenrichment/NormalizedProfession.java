package com.sovren.models.dataenrichment;

/**
 * A normalized profession and the confidence that the normalization is correct/fitting
 */
public class NormalizedProfession extends Profession {
    /**
     * A value from [0 - 1] indicating how well the input job title matched to the normalized profession.
     */
    public float Confidence;
}
