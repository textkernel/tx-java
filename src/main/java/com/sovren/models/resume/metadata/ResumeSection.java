// Copyright © 2020 Sovren Group, Inc. All rights reserved.
// This file is provided for use by, or on behalf of, Sovren licensees
// within the terms of their license of Sovren products or Sovren customers
// within the Terms of Service pertaining to the Sovren SaaS products.

package com.sovren.models.resume.metadata;

/**
* A section in the resume (work history, education, etc)
*/
public class ResumeSection {

    /** The first line of the section (zero-based)*/
    public int FirstLine;
    
    /** The last line of the section (zero-based)*/
    public int LastLine;
    
    /**
     * The type of section. Some possibilites:
     * <ul>
     * <li>CONTACT_INFO</li>
     * <li>EDUCATION</li>
     * <li>WORK_HISTORY</li>
     * <li>SKILLS</li>
     * <li>CERTIFICATIONS</li>
     * <li>etc...</li>
     * </ul>
     * <p>For all possible types, see <a href="https://docs.sovren.com/Documentation/ResumeParser#sov-generated-metadata-resumeuserarea">here</a>
    */
    public String SectionType;
    
    /** 
     * The exact text that was used to identify the beginning of the section.
     * If there was no text indicator and the location was calculated, then the value is {@code "CALCULATED"}
    */
    public String HeaderFound;
}
