// Copyright © 2023 Textkernel BV. All rights reserved.
// This file is provided for use by, or on behalf of, Textkernel licensees
// within the terms of their license of Textkernel products or Textkernel customers
// within the Terms of Service pertaining to the Textkernel SaaS products.

package com.textkernel.tx.models.resume.metadata;

import com.textkernel.tx.models.resume.SectionIdentifier;

import java.util.List;

/**
* A single resume quality issue
*/
public class ResumeQualityFinding {
        
    /**
     * A unique 3-digit code to identify what type of issue was found.
     * See all possibilities at our docs site <a href="https://developer.textkernel.com/Sovren/v10/resume-parser/overview/parser-output/">here</a>.
    */
    public String QualityCode;
        
    /**
     * If applicable, areas in the resume where this issue was found or that are affected by this issue.
    */
    public List<SectionIdentifier> SectionIdentifiers;
        
    /** A human-readable message explaining the issue that is being reported and possibly how to fix.*/
    public String Message;
}
