// Copyright © 2023 Textkernel BV. All rights reserved.
// This file is provided for use by, or on behalf of, Textkernel licensees
// within the terms of their license of Sovren products or Textkernel customers
// within the Terms of Service pertaining to the Textkernel SaaS products.

package com.textkernel.tx.models.api.dataenrichment.skills.response;
import com.textkernel.tx.models.api.ApiResponse;
import com.textkernel.tx.models.dataenrichment.NormalizedSkill;

import java.util.List;

/** One entry in the {@link ApiResponse#Value} from a 'NormalizeSkills' response */
public class NormalizeSkillsResponseValue {
    /** A list of skills objects. */
    public List<NormalizedSkill> Skills;
}