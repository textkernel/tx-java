// Copyright © 2023 Textkernel BV. All rights reserved.
// This file is provided for use by, or on behalf of, Textkernel licensees
// within the terms of their license of Sovren products or Textkernel customers
// within the Terms of Service pertaining to the Textkernel SaaS products.

package com.textkernel.tx.models.api.dataenrichment.ontology.response;
import com.textkernel.tx.models.api.ApiResponse;

/** One entry in the {@link ApiResponse#Value} from a 'skills Similarity Score' response */
public class SkillsSimilarityScoreResponseValue {
    /** A value from [0 - 1] representing how closely related skill set A and skill set B are, based on the relations between skills. */
    public float SimilarityScore;
}