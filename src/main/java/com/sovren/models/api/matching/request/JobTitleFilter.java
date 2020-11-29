package com.sovren.models.api.matching.request;

/**
* Criteria for filtering on a specific job title
*/
public class JobTitleFilter {

    /**
     * The name of the Job Title. Supports (*, ?) wildcard characters after the third character
     * in the term as defined in https://docs.sovren.com/Documentation/AIMatching#ai-filtering-special-operators
    */
    public String Title;

    /** Whether or not the job title must be a current job title*/
    public boolean IsCurrent;
}
