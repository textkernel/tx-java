// Copyright © 2020 Sovren Group, Inc. All rights reserved.
// This file is provided for use by, or on behalf of, Sovren licensees
// within the terms of their license of Sovren products or Sovren customers
// within the Terms of Service pertaining to the Sovren SaaS products.

package com.sovren.models.api.dataenrichment.skills.request;

import java.util.List;

import com.sovren.models.api.dataenrichment.AutocompleteRequest;

/** Request body for a 'SkillsAutocomplete' request */
public class SkillsAutoCompleteRequest extends AutocompleteRequest {
    /** If specified, only these types of skills will be returned. The following values are acceptable: Professional, IT, Language, Soft, All. */
    public List<String> Types;
}