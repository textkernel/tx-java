// Copyright © 2020 Sovren Group, Inc. All rights reserved.
// This file is provided for use by, or on behalf of, Sovren licensees
// within the terms of their license of Sovren products or Sovren customers
// within the Terms of Service pertaining to the Sovren SaaS products.

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
    public boolean MatchedFromList;
}
