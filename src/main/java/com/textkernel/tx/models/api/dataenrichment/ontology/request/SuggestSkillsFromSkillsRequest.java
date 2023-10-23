// Copyright © 2023 Textkernel BV. All rights reserved.
// This file is provided for use by, or on behalf of, Textkernel licensees
// within the terms of their license of Textkernel products or Textkernel customers
// within the Terms of Service pertaining to the Textkernel SaaS products.

package com.textkernel.tx.models.api.dataenrichment.ontology.request;

import java.util.List;

import com.textkernel.tx.models.api.dataenrichment.ontology.response.SkillScore;

/** Request body for a 'SuggestProfessions' request  */
public class SuggestSkillsFromSkillsRequest {
    /** The skills for which the service should return related skills. The list can contain up to 50 skills. */
    public List<SkillScore> Skills;
    /** The maximum amount of suggested skills returned. If not specified this parameter defaults to 25. */
    public int Limit = 25;
    /** The language to use for the returned descriptions. */
    public String OutputLanguage;
}