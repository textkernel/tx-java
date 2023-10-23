// Copyright © 2023 Textkernel BV. All rights reserved.
// This file is provided for use by, or on behalf of, Textkernel licensees
// within the terms of their license of Sovren products or Textkernel customers
// within the Terms of Service pertaining to the Textkernel SaaS products.

package com.textkernel.tx.models.api.matching.ui;

/** Used to change the order or titles of the filters in the Matching UI*/
public class FilterToShow {
    /**
    * One of:
    * <ul>
    * <li>JOB_TITLES</li>
    * <li>EXPERIENCE</li>
    * <li>SKILLS</li>
    * <li>INDUSTRIES</li>
    * <li>BOOLEAN</li>
    * <li>LANGUAGES</li>
    * <li>CERTIFICATIONS</li>
    * <li>EXECUTIVE_TYPES</li>
    * <li>LOCATION</li>
    * <li>EDUCATION</li>
    * <li>EMPLOYERS</li>
    * <li>TAGS</li>
    * <li>METADATA</li>
    * <li>UNCOMMON</li>
    * </ul>
    */
    public String FilterId;

    /**
    * An optional value to override the title that is shown for a specific filter. If not specified, the defaults are as below:
    * <ul>
    * <li>Job Titles</li>
    * <li>Experience</li>
    * <li>Skills</li>
    * <li>Industries</li>
    * <li>Boolean or Semantic Search</li>
    * <li>Spoken Languages</li>
    * <li>Certifications</li>
    * <li>Executive Types</li>
    * <li>Location</li>
    * <li>Education</li>
    * <li>Employers</li>
    * <li>User-Defined Tags</li>
    * <li>Document Metadata</li>
    * <li>Uncommon Filters</li>
    * </ul>
    */
    public String Title;
}
