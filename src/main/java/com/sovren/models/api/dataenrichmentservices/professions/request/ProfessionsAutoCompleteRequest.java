// Copyright © 2020 Sovren Group, Inc. All rights reserved.
// This file is provided for use by, or on behalf of, Sovren licensees
// within the terms of their license of Sovren products or Sovren customers
// within the Terms of Service pertaining to the Sovren SaaS products.

package com.sovren.models.api.dataenrichmentservices.professions.request;

public class ProfessionsAutoCompleteRequest {
    public String Prefix;
    public int Limit = 10;
    public List<String> Categories;
    public List<String> Languages;
    public string OutputLanguage = "en";
}