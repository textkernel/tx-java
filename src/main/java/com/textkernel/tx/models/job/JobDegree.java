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

    /** The normalized, local education level based on the job's country. Returns the Code ID based on the table found <a href="https://developer.textkernel.com/tx-platform/v10/job-order-parser/api/?h=Value.JobData.Degrees[i].LocalEducationLevel">here</a>.*/
    public String LocalEducationLevel;
}
