package com.sovren.models.api.matching.response;

/**
* Details about the score for a specific category
*/
public class EducationScoreData extends CategoryScoreData {
    
    /** Requested level of education.*/
    public String ExpectedEducation;
    
    /** Actual level of education found.*/
    public String ActualEducation;
    
    /**
     * How the {@link #ActualEducation} compares to the {@link #ExpectedEducation}. One of:
     * <ul>
     * <li>DoesNotMeetExpected</li>
     * <li>MeetsExpected</li>
     * <li>ExceedsExpected</li>
     * </ul>
    */
    public String Comparison;
}
