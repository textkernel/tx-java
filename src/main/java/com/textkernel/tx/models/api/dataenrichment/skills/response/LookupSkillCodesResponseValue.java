// Copyright © 2023 Textkernel BV. All rights reserved.
// This file is provided for use by, or on behalf of, Textkernel licensees
// within the terms of their license of Textkernel products or Textkernel customers
// within the Terms of Service pertaining to the Textkernel SaaS products.

package com.textkernel.tx.models.api.dataenrichment.skills.response;
import com.textkernel.tx.models.api.ApiResponse;
import com.textkernel.tx.models.dataenrichment.Skill;

import java.util.List;

/** One entry in the {@link ApiResponse#Value} from a 'LookupSkills' response */
public class LookupSkillCodesResponseValue {
    /** List of skills in from the skills taxonomy. */
    public List<Skill> Skills;
}