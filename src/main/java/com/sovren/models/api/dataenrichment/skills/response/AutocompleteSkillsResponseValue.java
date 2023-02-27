// Copyright © 2020 Sovren Group, Inc. All rights reserved.
// This file is provided for use by, or on behalf of, Sovren licensees
// within the terms of their license of Sovren products or Sovren customers
// within the Terms of Service pertaining to the Sovren SaaS products.

package com.sovren.models.api.dataenrichment.skills.response;
import com.sovren.models.api.ApiResponse;
import com.sovren.models.dataenrichment.Skill;

import java.util.List;

/** One entry in the {@link ApiResponse#Value} from a 'SkillsAutocomplete' response */
public class AutocompleteSkillsResponseValue {
    /** A list of skills based on the given Prefix. */
    public List<Skill> Skills;
}