// Copyright © 2023 Textkernel BV. All rights reserved.
// This file is provided for use by, or on behalf of, Textkernel licensees
// within the terms of their license of Textkernel products or Textkernel customers
// within the Terms of Service pertaining to the Textkernel SaaS products.

package com.textkernel.tx.models.resume.metadata;

import java.util.List;

/**
* A list of ResumeQualityFinding of the same level/severity
*/
public class ResumeQualityAssessment {
        
    /**
     * The level/severity of this assessment. One of:
     * <p>-{@link ResumeQualityLevel#FatalProblem}
     * <p>-{@link ResumeQualityLevel#MajorIssue}
     * <p>-{@link ResumeQualityLevel#DataMissing}
     * <p>-{@link ResumeQualityLevel#SuggestedImprovement}
     * */
    public String Level;
        
    /** A list of findings of the same severity/level*/
    public List<ResumeQualityFinding> Findings;
}
