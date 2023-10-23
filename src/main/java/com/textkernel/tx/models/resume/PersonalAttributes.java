// Copyright © 2023 Textkernel BV. All rights reserved.
// This file is provided for use by, or on behalf of, Textkernel licensees
// within the terms of their license of Sovren products or Textkernel customers
// within the Terms of Service pertaining to the Textkernel SaaS products.

package com.textkernel.tx.models.resume;

import java.util.List;
import com.textkernel.tx.models.SovrenDate;

/**
* Personal attributes found on a resume
*/
public class PersonalAttributes {

    /** The availability of the candidate*/
    public String Availability;

    /** The birthplace of the candidate*/
    public String Birthplace;

    /** The current location listed on the resume*/
    public String CurrentLocation;

    /** The current salary listed on the resume*/
    public Salary CurrentSalary;

    /** The date of birth given on the resume*/
    public SovrenDate DateOfBirth;

    /** A driving license listed on the resume*/
    public String DrivingLicense;

    /** The family composition*/
    public String FamilyComposition;

    /** The candidate's father's name listed on the resume*/
    public String FathersName;

    /** The candidate's gender listed on the resume*/
    public String Gender;

    /** Used in Chinese resumes*/
    public String HukouCity;

    /** Used in Chinese resumes*/
    public String HukouArea;

    /** The marital status listed on the resume*/
    public String MaritalStatus;

    /** The candidate's mother's maiden name listed on the resume*/
    public String MothersMaidenName;

    /** The candidate's mother tongue (native language) listed on the resume*/
    public String MotherTongue;

    /** Any national identities provided on the resume*/
    public List<NationalIdentity> NationalIdentities;

    /** The candidate's nationality listed on the resume*/
    public String Nationality;

    /** The candidate's passport number listed on the resume*/
    public String PassportNumber;

    /** The candidate's preferred location listed on the resume*/
    public String PreferredLocation;

    /** The candidate's required salary listed on the resume*/
    public Salary RequiredSalary;

    /** The candidate's visa status listed on the resume*/
    public String VisaStatus;

    /** Whether the candidate is willing to relocate*/
    public String WillingToRelocate;
}
