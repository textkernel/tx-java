// Copyright © 2020 Sovren Group, Inc. All rights reserved.
// This file is provided for use by, or on behalf of, Sovren licensees
// within the terms of their license of Sovren products or Sovren customers
// within the Terms of Service pertaining to the Sovren SaaS products.

package com.sovren.models.api.dataenrichmentservices.skills.response;

/** A matched skill that was found in the text provided. */
public class SkillMatch {
    /** The index of the first character of the match (0-based) */
    public int BeginSpan;
    /** The index of the last character of the match (0-based). */
    public int EndSpan;
    /** Likelihood that the matched term actually refers to a skill in the context of the text. */
    public float Likelihood;
    /** The actual term that was found as evidence of this skill (the substring from BeginSpan to EndSpan). */
    public String RawText;
}