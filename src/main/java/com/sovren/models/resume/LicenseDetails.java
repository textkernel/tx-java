package com.sovren.models.resume;

/**
* A license found on a resume. These are professional licenses, not driving licenses. For driving licenses, see Sovren.Models.Resume.PersonalAttributes.DrivingLicense
*/
public class LicenseDetails {

    /** The name of the license*/
    public String Name;

    /**
     * {@code true} if Sovren found this by matching to a known list of licenses.
     * {@code false} if Sovren found this by analyzing the context and determining it was a license.
    */
    public boolean MatchedToList;

    /** The full text where Sovren found the license*/
    public String FoundInContext;
}
