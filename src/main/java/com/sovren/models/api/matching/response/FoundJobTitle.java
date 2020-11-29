package com.sovren.models.api.matching.response;

/**
* Information about a job title match
*/
public class FoundJobTitle {
    
    /** Exact term found.*/
    public String RawTerm;
    
    /** Original term that the variation was derived from (or {@code null} if the {@link #RawTerm} was an exact match)*/
    public String VariationOf;
    
    /** {@code true} when the job title found is in the current time-frame.*/
    public boolean IsCurrent;
}
