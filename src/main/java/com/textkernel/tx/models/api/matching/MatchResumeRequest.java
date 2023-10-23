// Copyright © 2020 Sovren Group, Inc. All rights reserved.
// This file is provided for use by, or on behalf of, Sovren licensees
// within the terms of their license of Sovren products or Sovren customers
// within the Terms of Service pertaining to the Sovren SaaS products.

package com.textkernel.tx.models.api.matching;

import com.textkernel.tx.models.api.matching.request.MatchRequest;
import com.textkernel.tx.models.resume.ParsedResume;

/**
* Request body for a Match request
*/
public class MatchResumeRequest extends MatchRequest {
    
    /** The resume to match. This should be generated by parsing a resume with the Sovren Resume Parser.*/
    public ParsedResume ResumeData;
}
