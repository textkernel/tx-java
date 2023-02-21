// Copyright © 2020 Sovren Group, Inc. All rights reserved.
// This file is provided for use by, or on behalf of, Sovren licensees
// within the terms of their license of Sovren products or Sovren customers
// within the Terms of Service pertaining to the Sovren SaaS products.

package com.sovren.models.api.dataenrichment.skills.response;

import java.util.List;

/** One entry in the {@link ApiResponse#Value} from a 'ExtractSkills' response */
public class ExtractSkillsResponseValue {
    /** Whether the input text was truncated or not due to length. */
    public boolean Truncated;
    /** A list of extracted skills. */
    public List<ExtractedSkill> Skills;
}