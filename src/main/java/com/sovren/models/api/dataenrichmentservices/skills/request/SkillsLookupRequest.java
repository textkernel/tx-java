// Copyright © 2020 Sovren Group, Inc. All rights reserved.
// This file is provided for use by, or on behalf of, Sovren licensees
// within the terms of their license of Sovren products or Sovren customers
// within the Terms of Service pertaining to the Sovren SaaS products.

package com.sovren.models.api.dataenrichmentservices.skills.request;

import java.util.List;

public class SkillsLookupRequest {
    public List<String> CodeIds;
    public List<String> Categories;
    public String OutputLanguage = "en";
}