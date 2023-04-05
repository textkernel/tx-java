// Copyright © 2020 Sovren Group, Inc. All rights reserved.
// This file is provided for use by, or on behalf of, Sovren licensees
// within the terms of their license of Sovren products or Sovren customers
// within the Terms of Service pertaining to the Sovren SaaS products.

package com.sovren.models.dataenrichment;

import java.util.List;

/** An extracted, normalized skill from the Skills taxonomy. */
public class ExtractedSkill extends SkillAndConfidence {
    /** A list of matches where this skill was found in the text. */
    public List<SkillMatch> Matches;
}