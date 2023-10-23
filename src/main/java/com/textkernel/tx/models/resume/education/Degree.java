// Copyright © 2020 Sovren Group, Inc. All rights reserved.
// This file is provided for use by, or on behalf of, Sovren licensees
// within the terms of their license of Sovren products or Sovren customers
// within the Terms of Service pertaining to the Sovren SaaS products.

package com.textkernel.tx.models.resume.education;

import com.textkernel.tx.models.resume.NormalizedString;

/**
* An educational degree
*/
public class Degree {

    /** The name of the degree*/
    public NormalizedString Name;
    
    /**
     * These values are not very global-friendly, but the Parser does normalize all degrees
     * to one of these pre-defined types. This list is sorted, as well as possible, by increasing
     * level of education, although there are certainly ambiguities from one discipline to
     * another, such as whether professional is above or below bachelors. Here are the possible values:
     * <ul>
     * <li>lessThanHighSchool</li>
     * <li>specialeducation</li>
     * <li>someHighSchoolOrEquivalent</li>
     * <li>ged</li>
     * <li>certification</li>
     * <li>vocational</li>
     * <li>secondary</li>
     * <li>highSchoolOrEquivalent</li>
     * <li>someCollege</li>
     * <li>HND_HNC_OrEquivalent</li>
     * <li>ASc</li>
     * <li>associates</li>
     * <li>international</li>
     * <li>professional</li>
     * <li>postprofessional</li>
     * <li>BSc</li>
     * <li>bachelors</li>
     * <li>somePostgraduate</li>
     * <li>MBA</li>
     * <li>MSc</li>
     * <li>masters</li>
     * <li>intermediategraduate</li>
     * <li>doctorate</li>
     * <li>postdoctorate</li>
     * </ul>
    */
    public String Type;
}
