// Copyright © 2020 Sovren Group, Inc. All rights reserved.
// This file is provided for use by, or on behalf of, Sovren licensees
// within the terms of their license of Sovren products or Sovren customers
// within the Terms of Service pertaining to the Sovren SaaS products.

package com.textkernel.tx.models.api.dataenrichment.ontology.response;
import com.textkernel.tx.models.api.ApiResponse;
import java.util.List;

/** One entry in the {@link ApiResponse#Value} from a 'SuggestSkills' response */
public class SuggestSkillsResponseValue {
    /** A list of skills related to the given professions. */
    public List<SkillScore> SuggestedSkills;
}