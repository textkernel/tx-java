// Copyright © 2023 Textkernel BV. All rights reserved.
// This file is provided for use by, or on behalf of, Textkernel licensees
// within the terms of their license of Textkernel products or Textkernel customers
// within the Terms of Service pertaining to the Textkernel SaaS products.

package com.textkernel.tx.models.api.parsing;

import com.textkernel.tx.models.api.ApiResponse;
import com.textkernel.tx.models.api.ApiResponseInfoLite;
import com.textkernel.tx.models.resume.ParsedResume;

/**
* The {@link ApiResponse#Value} from a Parse response
*/
public class ParseResumeResponseValue extends BaseParseResponseValue {
    
    /** The main output from the Resume Parser*/
    public ParsedResume ResumeData;
    
    /**
     * Similar to {@link #ResumeData}, but with all of the 
     * Personally Identifiable Information (PII) redacted. For example,
     * this property will contain no {@link ParsedResume#ContactInformation}.
    */
    public ParsedResume RedactedResumeData;

    /**
     * Information about the FlexRequests transaction, if any were provided.
    */
    public FlexResponse FlexResponse;

    /** Information about the status of education normalization during the parse */
    public ApiResponseInfoLite EducationNormalizationResponse; 
}
