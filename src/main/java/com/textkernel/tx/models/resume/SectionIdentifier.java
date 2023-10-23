// Copyright © 2020 Sovren Group, Inc. All rights reserved.
// This file is provided for use by, or on behalf of, Sovren licensees
// within the terms of their license of Sovren products or Sovren customers
// within the Terms of Service pertaining to the Sovren SaaS products.

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
