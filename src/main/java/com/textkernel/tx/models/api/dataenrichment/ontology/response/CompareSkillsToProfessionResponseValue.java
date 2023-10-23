// Copyright © 2023 Textkernel BV. All rights reserved.
// This file is provided for use by, or on behalf of, Textkernel licensees
// within the terms of their license of Sovren products or Textkernel customers
// within the Terms of Service pertaining to the Textkernel SaaS products.

package com.textkernel.tx.models.api.dataenrichment.ontology.response;
import com.textkernel.tx.models.api.ApiResponse;
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