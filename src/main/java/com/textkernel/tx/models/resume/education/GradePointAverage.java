// Copyright © 2023 Textkernel BV. All rights reserved.
// This file is provided for use by, or on behalf of, Textkernel licensees
// within the terms of their license of Textkernel products or Textkernel customers
// within the Terms of Service pertaining to the Textkernel SaaS products.

package com.textkernel.tx.models.resume.education;

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
    
    /**
     * The {@link #Score}, normalized to a 0.0-1.0 scale, with 1.0 being the top mark. 
     * This takes into account different min/max values and whether high or low numbers 
     * are ranked higher. This makes it possible/valid to compare GPAs across various scales.
     * For example:
     * <ul>
     * <li>USA degree with GPA of 3.5 / 4.0 = 0.875</li>
     * <li>German degree with 1.5 / 6.0 = 0.916</li>
     * </ul>
    */
    public double NormalizedScore;
}
