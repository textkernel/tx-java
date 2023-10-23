// Copyright © 2020 Sovren Group, Inc. All rights reserved.
// This file is provided for use by, or on behalf of, Sovren licensees
// within the terms of their license of Sovren products or Sovren customers
// within the Terms of Service pertaining to the Sovren SaaS products.

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