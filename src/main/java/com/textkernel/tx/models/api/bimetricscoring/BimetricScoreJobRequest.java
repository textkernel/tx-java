// Copyright © 2023 Textkernel BV. All rights reserved.
// This file is provided for use by, or on behalf of, Textkernel licensees
// within the terms of their license of Textkernel products or Textkernel customers
// within the Terms of Service pertaining to the Textkernel SaaS products.

package com.textkernel.tx.models.api.bimetricscoring;

/**
* Request body for a 'BimetricScore' request
*/
public class BimetricScoreJobRequest extends BimetricScoreRequest {

    /** 
     * The job to use as the 'source' document for the bimetric score.
     * <p>All the target documents will be scored against this job.
    */
    public ParsedJobWithId SourceJob;
}
