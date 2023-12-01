// Copyright © 2023 Textkernel BV. All rights reserved.
// This file is provided for use by, or on behalf of, Textkernel licensees
// within the terms of their license of Textkernel products or Textkernel customers
// within the Terms of Service pertaining to the Textkernel SaaS products.

package com.textkernel.tx.models.resume;

/**
* A language competency (fluent in, can read, can write, etc) found on a resume
*/
public class LanguageCompetency {

    /** The language name*/
    public String Language;

    /** The two-letter ISO 639-1 code for the language*/
    public String LanguageCode;

    /** The full text where this language competency was found*/
    public String FoundInContext;
}
