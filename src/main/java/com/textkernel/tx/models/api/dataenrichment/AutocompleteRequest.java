// Copyright © 2020 Sovren Group, Inc. All rights reserved.
// This file is provided for use by, or on behalf of, Sovren licensees
// within the terms of their license of Sovren products or Sovren customers
// within the Terms of Service pertaining to the Sovren SaaS products.

package com.sovren.models.api.dataenrichment;

import java.util.List;

/** Request body for a 'ProfessionsAutocomplete' request */
public class AutocompleteRequest {
    /** The job title prefix to be completed. Must contain at least 1 character. */
    public String Prefix;
    /** The maximum number of returned professions. The default is 10 and the maximum is 100. */
    public int Limit = 10;
    /** The language(s) used to search for matching professions (the language of the provided Prefix). A maximum of 5 languages can be provided. Must be one of the supported <a href="https://sovren.com/technical-specs/latest/rest-api/data-enrichment/overview/#professions-languages">ISO codes</a>. */
    public List<String> Languages;
    /** The language to ouput the found professions in (default is 'en'). Must be one of the supported <a href="https://sovren.com/technical-specs/latest/rest-api/data-enrichment/overview/#professions-languages">ISO codes</a>. */
    public String OutputLanguage = "en";
}