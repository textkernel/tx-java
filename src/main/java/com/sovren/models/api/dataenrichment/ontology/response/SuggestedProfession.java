// Copyright © 2020 Sovren Group, Inc. All rights reserved.
// This file is provided for use by, or on behalf of, Sovren licensees
// within the terms of their license of Sovren products or Sovren customers
// within the Terms of Service pertaining to the Sovren SaaS products.

package com.sovren.models.api.dataenrichment.ontology.response;

import java.util.List;

/** A profession that was most relevant to the given skill. */
public class SuggestedProfession {
    /** The list of skills relevant to this profession but missing from the given list of skills in the request. This will only be returned if the {@link SuggestProfessionsRequest#ReturnMissingSkills} flag is set to true. */
    public List<SkillScore> MissingSkills;
    /** A value from [0 - 1] indicating how relative the given skills are to this profession. */
    public float Score;
    /** The code ID of the profession in the <a href="https://sovren.com/technical-specs/latest/rest-api/data-enrichment-services/overview/#professions-taxonomies">Sovren Professions Taxonomy</a>. */
    public int CodeId;
}