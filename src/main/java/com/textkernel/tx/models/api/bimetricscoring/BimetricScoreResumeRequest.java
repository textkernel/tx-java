// Copyright © 2020 Sovren Group, Inc. All rights reserved.
// This file is provided for use by, or on behalf of, Sovren licensees
// within the terms of their license of Sovren products or Sovren customers
// within the Terms of Service pertaining to the Sovren SaaS products.

package com.textkernel.tx.models.api.bimetricscoring;

/**
* Request body for a 'BimetricScore' request
*/
public class BimetricScoreResumeRequest extends BimetricScoreRequest {

    /** 
     * The resume to use as the 'source' document for the bimetric score.
     * <p>All the target documents will be scored against this resume.
    */
    public ParsedResumeWithId SourceResume;
}
