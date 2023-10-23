// Copyright © 2023 Textkernel BV. All rights reserved.
// This file is provided for use by, or on behalf of, Textkernel licensees
// within the terms of their license of Sovren products or Textkernel customers
// within the Terms of Service pertaining to the Textkernel SaaS products.

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
