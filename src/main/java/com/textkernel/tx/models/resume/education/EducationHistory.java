// Copyright © 2020 Sovren Group, Inc. All rights reserved.
// This file is provided for use by, or on behalf of, Sovren licensees
// within the terms of their license of Sovren products or Sovren customers
// within the Terms of Service pertaining to the Sovren SaaS products.

package com.sovren.models.resume.education;

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
