// Copyright © 2023 Textkernel BV. All rights reserved.
// This file is provided for use by, or on behalf of, Textkernel licensees
// within the terms of their license of Textkernel products or Textkernel customers
// within the Terms of Service pertaining to the Textkernel SaaS products.

package com.textkernel.tx.models.api.dataenrichment.skills.response;
import com.textkernel.tx.models.api.ApiResponse;
import com.textkernel.tx.models.dataenrichment.Skill;

import java.util.List;

/** One entry in the {@link ApiResponse#Value} from a 'SkillsAutocomplete' response */
public class AutocompleteSkillsResponseValue {
    /** A list of skills based on the given Prefix. */
    public List<Skill> Skills;
}