package com.sovren.models.api.matching.response;

/**
* {@inheritDoc}
*/
public class ManagementLevelScoreData extends CategoryScoreData {
    
    /** Actual management level found.*/
    public String Actual;
    
    /** Requested management level.*/
    public String Desired;
    
    /** {@code true} when the duration of management experience matches in the source and target documents.*/
    public boolean AmountOfExperienceMatches;
}
