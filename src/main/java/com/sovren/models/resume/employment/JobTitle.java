package com.sovren.models.resume.employment;

import java.util.List;

/**
* A job title found in a resume
*/
public class JobTitle {
    
    /** The raw text as it was found in the resume*/
    public String Raw;
    
    /** The normalized job title*/
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
    
    /** Any variations of this job title that might be useful for matching*/
    public List<String> Variations;
}
