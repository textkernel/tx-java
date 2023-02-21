// Copyright © 2020 Sovren Group, Inc. All rights reserved.
// This file is provided for use by, or on behalf of, Sovren licensees
// within the terms of their license of Sovren products or Sovren customers
// within the Terms of Service pertaining to the Sovren SaaS products.

package com.sovren.models.api.dataenrichment.ontology.response;

import java.util.List;

/** One entry in the {@link ApiResponse#Value} from a 'CompareSkillsToProfession' response */
public class CompareSkillsToProfessionResponseValue {
    /** A value from[0 - 1] indicating the similarity of the skill set and profession. */
    public float SimilarityScore;
    /** A list of common skills between skill set and profession. */
    public List<SkillScore> CommonSkills;
    /** The list of given skill IDs that are not associated to the given profession. */
    public List<String> InputSkillsNotInProfession;
    /** A list of skills associated with the profession but missing from list of provided skills. */
    public List<SkillScore> MissingSkillsFoundInProfession;
}