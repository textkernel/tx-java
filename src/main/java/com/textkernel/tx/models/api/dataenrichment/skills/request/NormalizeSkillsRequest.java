// Copyright © 2020 Sovren Group, Inc. All rights reserved.
// This file is provided for use by, or on behalf of, Sovren licensees
// within the terms of their license of Sovren products or Sovren customers
// within the Terms of Service pertaining to the Sovren SaaS products.

package com.sovren.models.api.dataenrichment.skills.request;

import java.util.List;

/** Request body for a 'NormalizeSkills' request */
public class NormalizeSkillsRequest {
    /** The list of skills to normalize (up to 50 skills, each skill may not exceed 100 characters). */
    public List<String> Skills;
    /** The language of the given skills. Must be one of the supported <a href="https://sovren.com/technical-specs/latest/rest-api/data-enrichment/overview/#professions-languages">ISO codes</a>. */
    public String Language;
    /** The language to use for the output skill descriptions. If not provided, defaults to the input language. Must be one of the supported <a href="https://sovren.com/technical-specs/latest/rest-api/data-enrichment/overview/#professions-languages">ISO codes</a>. */
    public String OutputLanguage;
}