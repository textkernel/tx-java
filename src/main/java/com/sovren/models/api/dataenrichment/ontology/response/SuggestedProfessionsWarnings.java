// Copyright © 2020 Sovren Group, Inc. All rights reserved.
// This file is provided for use by, or on behalf of, Sovren licensees
// within the terms of their license of Sovren products or Sovren customers
// within the Terms of Service pertaining to the Sovren SaaS products.

package com.sovren.models.api.dataenrichment.ontology.response;
import java.util.List;

/** Warnings when trying to suggest professions from skills. */
public class SuggestedProfessionsWarnings {
    /** A list of warnings about provided skills that do not have a profession relation. */
    public List<String> SkillsWithoutProfessionRelation;
    /** A list of warnings about provided skills that are invalid. */
    public List<String> InvalidSkills;
}