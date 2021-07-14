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
     * This is an advanced level feature. Please ignore the data in the Resume Quality output unless/until you have discussed its proper use with Sovren, and been approved to use it.<br><br>
     * The Resume Quality section output should NEVER IN ANY SENSE WHATSOEVER be used as an indication that the Parser has failed or performed poorly. 
     * The sole purpose of the Resume Quality section is to help you, the integrator, to understand substandard aspects of the candidate's resume. 
     * The majority of resumes will have at least one entry in this section. AGAIN, that does not mean that parsing "failed" or that the Parser needs fixing.<br><br>
     * Please recall that candidates' resumes fall within a bell curve. Some resumes are really well done. Some are horrible. Most fall into the Good to Pretty Good range. 
     * The Resume Quality section is designed to help you understand where the resume falls in that bell curve. Great resumes will parse great. Horrible resumes will parse poorly. 
     * That is a limitation of the quality of the resume. The Parser cannot fix candidate mistakes.<br><br>
     * For instance, the Resume Quality section may report that the candidate provided neither a phone nor an email address. Reporting that fact does not indicate that the Parser failed. 
     * The failure was that the candidate did not include a way to be contacted electronically. We cannot fix that, nor can you, the integrator. Only the candidate can.<br><br>
     * You should not use the Resume Quality section to communicate problems/suggestions to candidates unless you have a very sophisticated workflow and step-by-step improvement process. 
     * Otherwise, you will frustrate candidates and do more harm than good.<br><br>
     * The Resume Quality is a series of assessments of how well the resume conforms to best practices for constructing machine-readable resumes. Assessments are ordered by severity, 
     * from fatal problems (which nevertheless may not have caused an actual parsing problem), to suggested improvements. Each assessment contains a list of findings, 
     * describing the exact issue with the resume and a recommendation for how the candidate could resolve the issue.
    */
    public List<ResumeQualityAssessment> ResumeQuality;
        
    /** Used by Sovren to redact PII. See {@link ParseResumeResponseValue#RedactedResumeData} */
    public ReservedData ReservedData;
}
