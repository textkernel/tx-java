// Copyright © 2023 Textkernel BV. All rights reserved.
// This file is provided for use by, or on behalf of, Textkernel licensees
// within the terms of their license of Textkernel products or Textkernel customers
// within the Terms of Service pertaining to the Textkernel SaaS products.

package com.textkernel.tx.models.api.matching.response;

/**
 * Details about the score for the Education category
 */
public class EducationScoreData extends CategoryScoreData {
    
    /** Requested level of education.*/
    public String ExpectedEducation;
    
    /** Actual level of education found.*/
    public String ActualEducation;
    
    /**
     * How the {@link #ActualEducation} compares to the {@link #ExpectedEducation}. One of:
     * <ul>
     * <li>DoesNotMeetExpected</li>
     * <li>MeetsExpected</li>
     * <li>ExceedsExpected</li>
     * </ul>
    */
    public String Comparison;
}
