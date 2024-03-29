// Copyright © 2023 Textkernel BV. All rights reserved.
// This file is provided for use by, or on behalf of, Textkernel licensees
// within the terms of their license of Textkernel products or Textkernel customers
// within the Terms of Service pertaining to the Textkernel SaaS products.

package com.textkernel.tx.models.api.matching;

import com.textkernel.tx.models.api.matching.request.MatchRequest;
import com.textkernel.tx.models.resume.ParsedResume;

/**
* Request body for a Match request
*/
public class MatchResumeRequest extends MatchRequest {
    
    /** The resume to match. This should be generated by parsing a resume with the Resume Parser.*/
    public ParsedResume ResumeData;
}
