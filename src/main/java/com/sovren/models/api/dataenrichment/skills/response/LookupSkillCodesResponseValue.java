// Copyright © 2020 Sovren Group, Inc. All rights reserved.
// This file is provided for use by, or on behalf of, Sovren licensees
// within the terms of their license of Sovren products or Sovren customers
// within the Terms of Service pertaining to the Sovren SaaS products.

package com.sovren.models.api.dataenrichment.skills.response;
import com.sovren.models.api.ApiResponse;
import com.sovren.models.dataenrichment.Skill;

import java.util.List;

/** One entry in the {@link ApiResponse#Value} from a 'LookupSkills' response */
public class LookupSkillCodesResponseValue {
    /** List of skills in from the skills taxonomy. */
    public List<Skill> Skills;
}