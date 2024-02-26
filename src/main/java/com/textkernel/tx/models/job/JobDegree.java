// Copyright © 2023 Textkernel BV. All rights reserved.
// This file is provided for use by, or on behalf of, Textkernel licensees
// within the terms of their license of Textkernel products or Textkernel customers
// within the Terms of Service pertaining to the Textkernel SaaS products.

package com.textkernel.tx.models.job;

/**
* A preferred/required educational degree found in a job
*/
public class JobDegree {
    
    /** The name of the educational degree*/
    public String Name;
    
    /** The type of the educational degree*/
    public String Type;

    /** The normalized education level based on the job's country. See <a href="https://developer.textkernel.com/tx-platform/v10/resume-parser/overview/normalized-education-codes/#local-education-level">here</a>.*/
    public String LocalEducationLevel;

    /** The normalized education level based on an international standard. See <a href="https://developer.textkernel.com/tx-platform/v10/resume-parser/overview/normalized-education-codes/#international-education-level">here</a>.*/
    public String InternationalEducationLevel;
}
