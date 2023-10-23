// Copyright © 2020 Sovren Group, Inc. All rights reserved.
// This file is provided for use by, or on behalf of, Sovren licensees
// within the terms of their license of Sovren products or Sovren customers
// within the Terms of Service pertaining to the Sovren SaaS products.

package com.sovren.models.resume.metadata;

import com.sovren.models.ParsedDocumentMetadata;

/**
* A section in the resume (work history, education, etc)
*/
public class ResumeSection {

    /** The first line of the section (zero-based). This refers to the lines (delimited by newline) in the {@link ParsedDocumentMetadata#PlainText} */
    public int FirstLineNumber;
    
    /** The last line of the section (zero-based). This refers to the lines (delimited by newline) in the {@link ParsedDocumentMetadata#PlainText} */
    public int LastLineNumber;
    
    /**
     * The type of section. One of:
     * <ul>
     * <li>ARTICLES</li>
     * <li>AVAILABILITY</li>
     * <li>BOOKS</li>
     * <li>CERTIFICATIONS</li>
     * <li>CONFERENCE_PAPERS</li>
     * <li>CONTACT_INFO</li>
     * <li>EDUCATION</li>
     * <li>HOBBIES</li>
     * <li>IGNORE_DATA_AFTER</li>
     * <li>LANGUAGES</li>
     * <li>LICENSES</li>
     * <li>MILITARY</li>
     * <li>OBJECTIVE</li>
     * <li>OTHER_PUBLICATIONS</li>
     * <li>PATENTS</li>
     * <li>PERSONAL_INTERESTS_AND_ACCOMPLISHMENTS</li>
     * <li>PROFESSIONAL_AFFILIATIONS</li>
     * <li>QUALIFICATIONS_SUMMARY</li>
     * <li>REFERENCES</li>
     * <li>SECURITY_CLEARANCES</li>
     * <li>SKILLS</li>
     * <li>SPEAKING</li>
     * <li>SUMMARY</li>
     * <li>TRAINING</li>
     * <li>WORK_HISTORY</li>
     * <li>WORK_STATUS</li>
     * </ul>
    */
    public String SectionType;
    
    /** 
     * The exact text that was used to identify the beginning of the section.
     * If there was no text indicator and the location was calculated, then the value is {@code "CALCULATED"}
    */
    public String HeaderTextFound;
}
