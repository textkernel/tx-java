// Copyright © 2020 Sovren Group, Inc. All rights reserved.
// This file is provided for use by, or on behalf of, Sovren licensees
// within the terms of their license of Sovren products or Sovren customers
// within the Terms of Service pertaining to the Sovren SaaS products.

package com.sovren.models.api.dataenrichmentservices.skills.request;

import java.util.List;

/** Request body for a 'LookupSkills' request */
public class LookupSkillCodesRequest {
    /** The IDs of the skills to get details about. A maximum of 100 IDs can be requested. */
    public List<String> SkillIds;
    /** The language to use for the output skill descriptions. If not provided, defaults to en. If specified, must be one of the supported <a href="https://sovren.com/technical-specs/latest/rest-api/data-enrichment-services/overview/#professions-languages">ISO codes</a>. */
    public String OutputLanguage = "en";
}