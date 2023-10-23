// Copyright © 2023 Textkernel BV. All rights reserved.
// This file is provided for use by, or on behalf of, Textkernel licensees
// within the terms of their license of Sovren products or Textkernel customers
// within the Terms of Service pertaining to the Textkernel SaaS products.

package com.textkernel.tx.models.resume.education;

import java.util.List;
import com.textkernel.tx.models.resume.NormalizedString;
import com.textkernel.tx.models.Location;
import com.textkernel.tx.models.TxDate;
import com.textkernel.tx.models.TxPrimitive;

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
    
    /** The date graduated or education ended
     * @deprecated use {@link #EndDate} instead
    */
    public TxDate LastEducationDate;

    /** The date education started*/
    public TxDate StartDate;

    /** The date graduated or education ended*/
    public TxDate EndDate;
    
    /** Whether or not the candidate graduated*/
    public TxPrimitive<Boolean> Graduated;
}
