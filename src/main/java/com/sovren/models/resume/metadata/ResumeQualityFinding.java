// Copyright © 2020 Sovren Group, Inc. All rights reserved.
// This file is provided for use by, or on behalf of, Sovren licensees
// within the terms of their license of Sovren products or Sovren customers
// within the Terms of Service pertaining to the Sovren SaaS products.

package com.sovren.models.resume.metadata;

import com.sovren.models.resume.SectionIdentifier;

import java.util.List;

/**
* A single resume quality issue
*/
public class ResumeQualityFinding {
        
    /**
     * A unique 3-digit code to identify what type of issue was found.
     * See all possibilities at our docs site <a href="https://docs.sovren.com/#resume-quality">here</a>.
    */
    public String QualityCode;
        
    /**
     * If applicable, areas in the resume where this issue was found or that are affected by this issue.
    */
    public List<SectionIdentifier> Identifiers;
        
    /** A human-readable message explaining the issue that is being reported and possibly how to fix.*/
    public String Message;
}
