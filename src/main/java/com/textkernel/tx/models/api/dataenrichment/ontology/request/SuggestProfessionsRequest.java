// Copyright © 2023 Textkernel BV. All rights reserved.
// This file is provided for use by, or on behalf of, Textkernel licensees
// within the terms of their license of Sovren products or Textkernel customers
// within the Terms of Service pertaining to the Textkernel SaaS products.

package com.textkernel.tx.models.api.dataenrichment.ontology.request;

import java.util.List;

import com.textkernel.tx.models.api.dataenrichment.ontology.response.SkillScore;

/** Request body for a 'SuggestProfessions' request  */
public class SuggestProfessionsRequest {
    /** The skills used to return the most relevant professions. The list can contain up to 50 skills. */
    public List<SkillScore> Skills;
    /** Flag to enable returning a list of missing skills per suggested profession. */
    public boolean ReturnMissingSkills = false;
    /** The maximum amount of professions returned. If not specified this parameter defaults to 10. */
    public int Limit = 10;
    /** The language to use for the returned descriptions. */
    public String OutputLanguage;
}