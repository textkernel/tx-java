package com.sovren.models.resume.metadata;

import java.util.List;

/**
* A single resume quality issue
*/
public class ResumeQualityFinding {
        
    /**
     * A unique 3-digit code to identify what type of issue was found.
     * See all possibilities at our docs site <a href="https://docs.sovren.com/#resume-quality">here</a>.
    */
    public int QualityCode;
        
    /**
     * Areas in the resume where this issue was found or affected by this issue. These will be
     * section indexes, work history position identifiers, etc.
    */
    public List<String> Identifiers;
        
    /** A human-readable message explaining the issue that is being reported and possibly how to fix.*/
    public String Message;
}
