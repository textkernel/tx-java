// Copyright © 2023 Textkernel BV. All rights reserved.
// This file is provided for use by, or on behalf of, Textkernel licensees
// within the terms of their license of Textkernel products or Textkernel customers
// within the Terms of Service pertaining to the Textkernel SaaS products.

package com.textkernel.tx.models.dataenrichment;

import java.util.List;

/** An extracted, normalized skill from the Skills taxonomy. */
public class ExtractedSkill extends SkillAndConfidence {
    /** A list of matches where this skill was found in the text. */
    public List<SkillMatch> Matches;
}