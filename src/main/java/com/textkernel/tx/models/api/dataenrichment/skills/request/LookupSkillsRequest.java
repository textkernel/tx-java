// Copyright © 2023 Textkernel BV. All rights reserved.
// This file is provided for use by, or on behalf of, Textkernel licensees
// within the terms of their license of Sovren products or Textkernel customers
// within the Terms of Service pertaining to the Textkernel SaaS products.

package com.textkernel.tx.models.api.dataenrichment.skills.request;

import java.util.List;

/** Request body for a 'LookupSkills' request */
public class LookupSkillsRequest {
    /** The IDs of the skills to get details about. A maximum of 100 IDs can be requested. */
    public List<String> SkillIds;
    /** The language to use for the output skill descriptions. If not provided, defaults to en. If specified, must be one of the supported <a href="https://developer.textkernel.com/Sovren/v10/data-enrichment/overview/#professions-languages">ISO codes</a>. */
    public String OutputLanguage = "en";
}