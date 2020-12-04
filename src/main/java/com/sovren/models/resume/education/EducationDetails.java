// Copyright © 2020 Sovren Group, Inc. All rights reserved.
// This file is provided for use by, or on behalf of, Sovren licensees
// within the terms of their license of Sovren products or Sovren customers
// within the Terms of Service pertaining to the Sovren SaaS products.

package com.sovren.models.resume.education;

import java.util.List;
import com.sovren.models.resume.NormalizedString;
import com.sovren.models.Location;
import com.sovren.models.SovrenDate;
import com.sovren.models.SovrenPrimitive;

/**
* An education entry on a resume
*/
public class EducationDetails {
    
    /** The id of this education entry (one-based, so EDU-1 is the first, etc)*/
    public String Id;
    
    /** The raw text from the resume*/
    public String Text;
    
    /** The name of the school attended*/
    public NormalizedString SchoolName;
    
    /**
     * The type of the school attended. One of:
     * <ul>
     * <li>UNSPECIFIED</li>
     * <li>lowerSchool</li>
     * <li>highschool</li>
     * <li>secondary</li>
     * <li>trade</li>
     * <li>professional</li>
     * <li>vocational</li>
     * <li>community</li>
     * <li>college</li>
     * <li>university</li>
     * </ul>
    */
    public String SchoolType;
    
    /** The Country/Region/City of the school, if found*/
    public Location Location;
    
    /** The degree obtained (or worked toward)*/
    public Degree Degree;
    
    /** Any majors/areas of major focus*/
    public List<String> Majors;
    
    /** Any minors/areas of minor focus*/
    public List<String> Minors;
    
    /** The GPA/marks listed on the resume*/
    public GradePointAverage GPA;
    
    /** The date graduated or education ended*/
    public SovrenDate LastEducationDate;
    
    /** Whether or not the candidate graduated*/
    public SovrenPrimitive<Boolean> Graduated;
}
