package com.sovren.models.resume.employment;

import com.sovren.models.resume.NormalizedString;

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
