// Copyright © 2023 Textkernel BV. All rights reserved.
// This file is provided for use by, or on behalf of, Textkernel licensees
// within the terms of their license of Sovren products or Textkernel customers
// within the Terms of Service pertaining to the Textkernel SaaS products.

package com.textkernel.tx.models.api.dataenrichment.skills.request;

/** Request body for a 'ExtractSkills' request */
public class ExtractSkillsRequest {
    /** The text to extract skills from. There is a 24,000 character limit. */
    public String Text;
    /** The language of the input text. Must be one of the supported <a href="https://developer.textkernel.com/Sovren/v10/data-enrichment/overview/#professions-languages">ISO codes</a>. */
    public String Language;
    /** A value from [0 - 1] for the minimum confidence threshold for extracted skills. Lower values will return more skills, but also increase the likelihood of ambiguity-related errors. The recommended and default value is 0.5. */
    public float Threshold = 0.5f;
    /** The language to use for the output skill descriptions. If not provided, defaults to the input language. Must be one of the supported <a href="https://developer.textkernel.com/Sovren/v10/data-enrichment/overview/#professions-languages">ISO codes</a>. */
    public String OutputLanguage;
}