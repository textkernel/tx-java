// Copyright © 2023 Textkernel BV. All rights reserved.
// This file is provided for use by, or on behalf of, Textkernel licensees
// within the terms of their license of Textkernel products or Textkernel customers
// within the Terms of Service pertaining to the Textkernel SaaS products.

package com.textkernel.tx.models.resume;

/**
* A certification found on a resume
*/
public class Certification {

    /** The name of the certification*/
    public String Name;

    /**
     * {@code true} if this was found by matching to a known list of certifications.
     * {@code false} if this was found by analyzing the context and determining it was a certification.
    */
    public boolean MatchedFromList;
    
    /**
     * The parser generates several possible variations for some certifications to be used in AI Matching.
     * This greatly improves Matching, since different candidates have different ways of listing a certification.
     * If this certification is a generated variation of a certification found on the resume, this property will be
     * {@code true}, otherwise {@code false}.
    */
    public boolean IsVariation;
}
