// Copyright © 2020 Sovren Group, Inc. All rights reserved.
// This file is provided for use by, or on behalf of, Sovren licensees
// within the terms of their license of Sovren products or Sovren customers
// within the Terms of Service pertaining to the Sovren SaaS products.

package com.sovren.models.resume.metadata;

import java.util.List;
import com.sovren.models.ParsedDocumentMetadata;
import com.sovren.models.api.parsing.ParseResumeResponseValue;

/**
* Metadata about a parsed resume
*/
public class ResumeMetadata extends ParsedDocumentMetadata {
        
    /** A list of sections found in the resume*/
    public List<ResumeSection> FoundSections;
        
    /**
     * A list of quality assessments for the resume. These are very useful for
     * providing feedback to candidates about why their resume did not parse properly.
     * These can also be used to determine if a resume is 'high quality' enough to put into
     * your system.
    */
    public List<ResumeQualityAssessment> ResumeQuality;
        
    /** Used by Sovren to redact PII. See {@link ParseResumeResponseValue#RedactedResumeData} */
    public ReservedData ReservedData;
}
