// Copyright © 2023 Textkernel BV. All rights reserved.
// This file is provided for use by, or on behalf of, Textkernel licensees
// within the terms of their license of Sovren products or Textkernel customers
// within the Terms of Service pertaining to the Textkernel SaaS products.

package com.textkernel.tx.models.resume.education;

import java.util.List;

/**
* Information about education history found on a resume
*/
public class EducationHistory {
    
    /** The highest degree obtained by a candidate*/
    public Degree HighestDegree;
    
    /** All of the education details listed on a resume*/
    public List<EducationDetails> EducationDetails;
}
