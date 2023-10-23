// Copyright © 2023 Textkernel BV. All rights reserved.
// This file is provided for use by, or on behalf of, Textkernel licensees
// within the terms of their license of Textkernel products or Textkernel customers
// within the Terms of Service pertaining to the Textkernel SaaS products.

package com.textkernel.tx.models.api.dataenrichment.ontology.response;
import com.textkernel.tx.models.api.ApiResponse;
import java.util.List;

/** One entry in the {@link ApiResponse#Value} from a 'SuggestSkills' response */
public class SuggestSkillsResponseValue {
    /** A list of skills related to the given professions. */
    public List<SkillScore> SuggestedSkills;
}