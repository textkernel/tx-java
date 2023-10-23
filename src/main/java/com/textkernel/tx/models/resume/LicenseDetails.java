// Copyright © 2020 Sovren Group, Inc. All rights reserved.
// This file is provided for use by, or on behalf of, Sovren licensees
// within the terms of their license of Sovren products or Sovren customers
// within the Terms of Service pertaining to the Sovren SaaS products.

package com.textkernel.tx.models.resume;

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
    public boolean MatchedFromList;
}
