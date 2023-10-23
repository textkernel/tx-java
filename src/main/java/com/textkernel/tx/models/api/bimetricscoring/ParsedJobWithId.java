// Copyright © 2023 Textkernel BV. All rights reserved.
// This file is provided for use by, or on behalf of, Textkernel licensees
// within the terms of their license of Textkernel products or Textkernel customers
// within the Terms of Service pertaining to the Textkernel SaaS products.

package com.textkernel.tx.models.api.bimetricscoring;

import com.textkernel.tx.models.job.ParsedJob;

/**
* A Sovren.Models.Job.ParsedJob and id pair for a bimetric score request
*/
public class ParsedJobWithId implements IParsedDocWithId  {
    
    /** The id of the document (used in the response body)*/
    public String Id;

    /** A job generated by the job parser*/
    public ParsedJob JobData;

    @Override
    public String getDocId() {
        return Id;
    }
}
