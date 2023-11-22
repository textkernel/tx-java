// Copyright © 2023 Textkernel BV. All rights reserved.
// This file is provided for use by, or on behalf of, Textkernel licensees
// within the terms of their license of Textkernel products or Textkernel customers
// within the Terms of Service pertaining to the Textkernel SaaS products.

package com.textkernel.tx.models.api.dataenrichment.skills.request;

import java.util.List;

/** Request body for a 'NormalizeSkills' request */
public class NormalizeSkillsRequest {
    /** The list of skills to normalize (up to 50 skills, each skill may not exceed 100 characters). */
    public List<String> Skills;
    /** The language of the given skills. Must be one of the supported <a href="https://developer.textkernel.com/tx-platform/v10/data-enrichment/overview/#professions-languages">ISO codes</a>. */
    public String Language;
    /** The language to use for the output skill descriptions. If not provided, defaults to the input language. Must be one of the supported <a href="https://developer.textkernel.com/tx-platform/v10/data-enrichment/overview/#professions-languages">ISO codes</a>. */
    public String OutputLanguage;
}