package com.sovren.models.api.matching.request;

/**
* A range of revision dates in ISO 8601 (yyyy-MM-dd) format
*/
public class RevisionDateRange {
    
    /** the minimum (oldest) date in ISO 8601 (yyyy-MM-dd) format*/
    public String Minimum;
    
    /** the maximum (most recent) date in ISO 8601 (yyyy-MM-dd) format*/
    public String Maximum;
}
