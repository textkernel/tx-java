// Copyright © 2023 Textkernel BV. All rights reserved.
// This file is provided for use by, or on behalf of, Textkernel licensees
// within the terms of their license of Sovren products or Textkernel customers
// within the Terms of Service pertaining to the Textkernel SaaS products.

package com.textkernel.tx.models.api.dataenrichment.skills.request;

import java.util.List;

import com.textkernel.tx.models.api.dataenrichment.AutocompleteRequest;

/** Request body for a 'SkillsAutocomplete' request */
public class SkillsAutoCompleteRequest extends AutocompleteRequest {
    /** If specified, only these types of skills will be returned. The following values are acceptable: Professional, IT, Language, Soft, All. */
    public List<String> Types;
}