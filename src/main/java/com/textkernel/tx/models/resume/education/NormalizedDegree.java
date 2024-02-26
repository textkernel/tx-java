// Copyright © 2023 Textkernel BV. All rights reserved.
// This file is provided for use by, or on behalf of, Textkernel licensees
// within the terms of their license of Textkernel products or Textkernel customers
// within the Terms of Service pertaining to the Textkernel SaaS products.

package com.textkernel.tx.models.resume.education;

/**
* A normalized educational degree
*/
public class NormalizedDegree {

    /** One of the codes listed <a href="https://developer.textkernel.com/tx-platform/v10/resume-parser/overview/normalized-education-codes/">here</a>.*/
    public String Code;

    /** One of the descriptions listed <a href="https://developer.textkernel.com/tx-platform/v10/resume-parser/overview/normalized-education-codes/">here</a>.*/
    public String Description;
}
