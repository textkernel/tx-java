// Copyright © 2023 Textkernel BV. All rights reserved.
// This file is provided for use by, or on behalf of, Textkernel licensees
// within the terms of their license of Sovren products or Textkernel customers
// within the Terms of Service pertaining to the Textkernel SaaS products.

package com.textkernel.tx.models.api.dataenrichment.ontology.request;

import java.util.List;

import com.textkernel.tx.models.api.dataenrichment.ontology.response.SkillScore;

/** Request body for a 'SuggestProfessions' request  */
public class SkillsSimilarityScoreRequest {
    /** A set of skills (and optionally, scores) to score against the other set of skills. The list can contain up to 50 skills. */
    public List<SkillScore> SkillsA;
    /** A set of skills (and optionally, scores) to score against the other set of skills. The list can contain up to 50 skills. */
    public List<SkillScore> SkillsB;
}