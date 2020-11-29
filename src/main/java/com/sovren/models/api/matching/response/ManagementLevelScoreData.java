package com.sovren.models.api.matching.response;

/**
* Details about the score for the Management Level category
*/
public class ManagementLevelScoreData extends CategoryScoreData {
    
    /** Actual management level found.*/
    public String Actual;
    
    /** Requested management level.*/
    public String Desired;
    
    /** {@code true} when the duration of management experience matches in the source and target documents.*/
    public boolean AmountOfExperienceMatches;
}
