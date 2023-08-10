// Copyright © 2020 Sovren Group, Inc. All rights reserved.
// This file is provided for use by, or on behalf of, Sovren licensees
// within the terms of their license of Sovren products or Sovren customers
// within the Terms of Service pertaining to the Sovren SaaS products.

package com.sovren.models.api.dataenrichment.ontology.response;

/** A skill related to the given profession. */
public class SkillScore {
    /** A value from [0 - 1] indicating how relative this skill is to all of the given professions. */
    public float Score;
    /** The ID of the skill in the skills taxonomy. */
    public String Id;
    /** The description of the skill in the Skills Taxonomy. Will only be returned if OutputLanguage is specified in the request. This has no effect in a request body. */
    public String Description;
}