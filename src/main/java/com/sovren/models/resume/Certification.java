package com.sovren.models.resume;

/**
* A certification found on a resume
*/
public class Certification {

    /** The name of the certification*/
    public String Name;

    /**
     * {@code true} if Sovren found this by matching to a known list of certifications.
     * {@code false} if Sovren found this by analyzing the context and determining it was a certification.
    */
    public boolean MatchedToList;

    /** The full text where Sovren found the certification*/
    public String FoundInContext;
}
