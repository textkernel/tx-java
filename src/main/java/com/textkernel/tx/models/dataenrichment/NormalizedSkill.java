// Copyright © 2023 Textkernel BV. All rights reserved.
// This file is provided for use by, or on behalf of, Textkernel licensees
// within the terms of their license of Textkernel products or Textkernel customers
// within the Terms of Service pertaining to the Textkernel SaaS products.

package com.textkernel.tx.models.dataenrichment;

/** A normalized skill object. */
public class NormalizedSkill extends SkillAndConfidence {
    /** The raw text that matched to a skill description in the provided language. */
    public String RawText;
}