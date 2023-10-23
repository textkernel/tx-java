// Copyright © 2020 Sovren Group, Inc. All rights reserved.
// This file is provided for use by, or on behalf of, Sovren licensees
// within the terms of their license of Sovren products or Sovren customers
// within the Terms of Service pertaining to the Sovren SaaS products.

package com.textkernel.tx.models.api.dataenrichment.ontology.response;
import com.textkernel.tx.models.api.ApiResponse;
import java.util.List;

/** One entry in the {@link ApiResponse#Value} from a 'CompareProfessions' response */
public class CompareProfessionsResponseValue {
    /** A value from [0 - 1] indicating the similarity between the two professions. */
    public float SimilarityScore;
    /** A list of common skills for both professions. */
    public List<SkillScore> CommonSkills;
    /** A list of exclusive skills per profession. This list will have at most 2 entries (one for each profession you provided). */
    public List<ProfessionExclusiveSkills> ExclusiveSkillsByProfession;
}