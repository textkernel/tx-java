// Copyright © 2023 Textkernel BV. All rights reserved.
// This file is provided for use by, or on behalf of, Textkernel licensees
// within the terms of their license of Sovren products or Textkernel customers
// within the Terms of Service pertaining to the Textkernel SaaS products.

package com.textkernel.tx.models.api.dataenrichment.ontology.response;
import java.util.List;

/** Warnings when trying to suggest professions from skills. */
public class SuggestedProfessionsWarnings {
    /** A list of warnings about provided skills that do not have a profession relation. */
    public List<String> SkillsWithoutProfessionRelation;
    /** A list of warnings about provided skills that are invalid. */
    public List<String> InvalidSkills;
}