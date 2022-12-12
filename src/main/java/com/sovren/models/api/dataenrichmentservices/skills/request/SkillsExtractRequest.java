// Copyright © 2020 Sovren Group, Inc. All rights reserved.
// This file is provided for use by, or on behalf of, Sovren licensees
// within the terms of their license of Sovren products or Sovren customers
// within the Terms of Service pertaining to the Sovren SaaS products.

package com.sovren.models.api.dataenrichmentservices.skills.request;

public class SkillsExtractRequest {
    public String Text;
    public String Language;
    public float Threshold = 0.5f;
    public String OutputLanguage;
}