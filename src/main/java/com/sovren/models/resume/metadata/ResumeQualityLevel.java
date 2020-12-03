// Copyright © 2020 Sovren Group, Inc. All rights reserved.
// This file is provided for use by, or on behalf of, Sovren licensees
// within the terms of their license of Sovren products or Sovren customers
// within the Terms of Service pertaining to the Sovren SaaS products.

package com.sovren.models.resume.metadata;

/** The level/severity of a {@link ResumeQualityAssessment}*/
public class ResumeQualityLevel {

    /** Only minor issues were found*/
    public static ResumeQualityLevel SuggestedImprovement = new ResumeQualityLevel("Suggested Improvements");

    /** Some data was missing that should be included in a resume*/
    public static ResumeQualityLevel DataMissing = new ResumeQualityLevel("Data Missing");

    /** A major issue was found in the resume that will reduce the quality of parse results*/
    public static ResumeQualityLevel MajorIssue = new ResumeQualityLevel("Major Issues Found");

    /** A fatal issue was found in the resume. Parse results may have severe inaccuracies*/
    public static ResumeQualityLevel FatalProblem = new ResumeQualityLevel("Fatal Problems Found");

    /** The string value for this level/severity */
    public String Value;

    private ResumeQualityLevel(String value) {
        Value = value;
    }
}
