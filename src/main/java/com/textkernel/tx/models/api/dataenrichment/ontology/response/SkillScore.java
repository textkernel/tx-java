// Copyright © 2023 Textkernel BV. All rights reserved.
// This file is provided for use by, or on behalf of, Textkernel licensees
// within the terms of their license of Textkernel products or Textkernel customers
// within the Terms of Service pertaining to the Textkernel SaaS products.

package com.textkernel.tx.models.api.dataenrichment.ontology.response;

/** A skill related to the given profession. */
public class SkillScore {
    /** A value from [0 - 1] indicating how relative this skill is to all of the given professions. */
    public float Score;
    /** The ID of the skill in the skills taxonomy. */
    public String Id;
    /** The description of the skill in the Skills Taxonomy. Will only be returned if OutputLanguage is specified in the request. This has no effect in a request body. */
    public String Description;

    public SkillScore() {}
    public SkillScore(String id) {
        Id = id;
    }
}