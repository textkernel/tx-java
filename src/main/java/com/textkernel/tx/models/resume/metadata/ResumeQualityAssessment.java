// Copyright © 2020 Sovren Group, Inc. All rights reserved.
// This file is provided for use by, or on behalf of, Sovren licensees
// within the terms of their license of Sovren products or Sovren customers
// within the Terms of Service pertaining to the Sovren SaaS products.

package com.sovren.models.resume.metadata;

import java.util.List;

/**
* A list of Sovren.Models.Resume.Metadata.ResumeQualityFinding of the same level/severity
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
