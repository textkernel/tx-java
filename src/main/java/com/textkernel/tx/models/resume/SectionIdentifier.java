// Copyright © 2023 Textkernel BV. All rights reserved.
// This file is provided for use by, or on behalf of, Textkernel licensees
// within the terms of their license of Textkernel products or Textkernel customers
// within the Terms of Service pertaining to the Textkernel SaaS products.

package com.textkernel.tx.models.resume;

import com.textkernel.tx.models.resume.education.EducationDetails;
import com.textkernel.tx.models.resume.employment.Position;

/** Information about a particular section of a resume*/
public class SectionIdentifier {
    /** The section type */
    public String SectionType;

    /**
     * If applicable, the {@link Position#Id} or {@link EducationDetails#Id}
     */
    public String Id;
}
