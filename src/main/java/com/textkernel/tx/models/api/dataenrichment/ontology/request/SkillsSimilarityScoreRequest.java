// Copyright © 2020 Sovren Group, Inc. All rights reserved.
// This file is provided for use by, or on behalf of, Sovren licensees
// within the terms of their license of Sovren products or Sovren customers
// within the Terms of Service pertaining to the Sovren SaaS products.

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