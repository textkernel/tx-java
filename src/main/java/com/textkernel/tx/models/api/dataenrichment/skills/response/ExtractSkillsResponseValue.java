// Copyright © 2023 Textkernel BV. All rights reserved.
// This file is provided for use by, or on behalf of, Textkernel licensees
// within the terms of their license of Textkernel products or Textkernel customers
// within the Terms of Service pertaining to the Textkernel SaaS products.

package com.textkernel.tx.models.api.dataenrichment.skills.response;
import com.textkernel.tx.models.api.ApiResponse;
import com.textkernel.tx.models.dataenrichment.ExtractedSkill;

import java.util.List;

/** One entry in the {@link ApiResponse#Value} from a 'ExtractSkills' response */
public class ExtractSkillsResponseValue {
    /** Whether the input text was truncated or not due to length. */
    public boolean Truncated;
    /** A list of extracted skills. */
    public List<ExtractedSkill> Skills;
}