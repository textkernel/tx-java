// Copyright © 2020 Sovren Group, Inc. All rights reserved.
// This file is provided for use by, or on behalf of, Sovren licensees
// within the terms of their license of Sovren products or Sovren customers
// within the Terms of Service pertaining to the Sovren SaaS products.

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
