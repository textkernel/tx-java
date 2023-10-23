// Copyright © 2020 Sovren Group, Inc. All rights reserved.
// This file is provided for use by, or on behalf of, Sovren licensees
// within the terms of their license of Sovren products or Sovren customers
// within the Terms of Service pertaining to the Sovren SaaS products.

package com.textkernel.tx.models.job;

/**
* A preferred/required educational degree found in a job
*/
public class JobDegree {
    
    /** The name of the educational degree*/
    public String Name;
    
    /** The type of the educational degree*/
    public String Type;

    /** The normalized, local education level based on the job's country. Returns the Code ID based on the table found <a href="https://sovren.com/technical-specs/latest/rest-api/job-order-parser/api/?h=Value.JobData.Degrees[i].LocalEducationLevel">here</a>.*/
    public String LocalEducationLevel;
}
