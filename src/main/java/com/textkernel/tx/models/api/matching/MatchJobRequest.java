// Copyright © 2023 Textkernel BV. All rights reserved.
// This file is provided for use by, or on behalf of, Textkernel licensees
// within the terms of their license of Textkernel products or Textkernel customers
// within the Terms of Service pertaining to the Textkernel SaaS products.

package com.textkernel.tx.models.api.matching;

import com.textkernel.tx.models.api.matching.request.MatchRequest;
import com.textkernel.tx.models.job.ParsedJob;

/**
* Request body for a Match request
*/
public class MatchJobRequest extends MatchRequest {
    
    /** The job to match. This should be generated by parsing a job with the Job Parser.*/
    public ParsedJob JobData;
}
