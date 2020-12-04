// Copyright © 2020 Sovren Group, Inc. All rights reserved.
// This file is provided for use by, or on behalf of, Sovren licensees
// within the terms of their license of Sovren products or Sovren customers
// within the Terms of Service pertaining to the Sovren SaaS products.

package com.sovren.models.resume.education;

/**
* Information about a GPA (or equivalent)
*/
public class GradePointAverage {
    
    /** The score found in the resume*/
    public String Score;
    
    /** The scoring system used on the resume*/
    public String ScoringSystem;
    
    /** The max score in the {@link #ScoringSystem}*/
    public String MaxScore;
    
    /** The minimum score in the {@link #ScoringSystem}*/
    public String MinimumScore;
    
    /** The {@link #Score}, normalized to a 0.0-1.0 scale*/
    public double NormalizedScore;
}
