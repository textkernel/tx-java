package com.sovren.models.resume.military;

import com.sovren.models.SovrenDate;

/**
* Information about military post/job listed on a resume
*/
public class MilitaryDetails {

    /** The country that the military belongs to*/
    public String Country;

    /** The branch/name/rank for this post/job*/
    public MilitaryService Service;

    /** The start date for this post/job*/
    public SovrenDate StartDate;

    /** The end date for this post/job*/
    public SovrenDate EndDate;

    /** The full text where Sovren found this military post/job in the resume*/
    public String FoundInContext;
}
