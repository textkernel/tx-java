// Copyright © 2023 Textkernel BV. All rights reserved.
// This file is provided for use by, or on behalf of, Textkernel licensees
// within the terms of their license of Textkernel products or Textkernel customers
// within the Terms of Service pertaining to the Textkernel SaaS products.

package com.textkernel.tx.models.resume;

/**
* A license found on a resume. These are professional licenses, not driving licenses. For driving licenses, see tx.models.resume.PersonalAttributes.DrivingLicense
*/
public class LicenseDetails {

    /** The name of the license*/
    public String Name;

    /**
     * {@code true} if this was found by matching to a known list of licenses.
     * {@code false} if this was found this by analyzing the context and determining it was a license.
    */
    public boolean MatchedFromList;
}
