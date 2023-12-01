// Copyright © 2023 Textkernel BV. All rights reserved.
// This file is provided for use by, or on behalf of, Textkernel licensees
// within the terms of their license of Textkernel products or Textkernel customers
// within the Terms of Service pertaining to the Textkernel SaaS products.

package com.textkernel.tx.models.api.dataenrichment.ontology.request;

import java.util.List;
import com.textkernel.tx.models.api.dataenrichment.ontology.response.SkillScore;

/** Request body for a 'CompareSkillsToProfession' request */
public class CompareSkillsToProfessionRequest {
    /** The skills which should be compared against the given profession. The list can contain up to 50 skills.  */
    public List<SkillScore> Skills;
    /** The profession code ID from the <a href="https://developer.textkernel.com/tx-platform/v10/data-enrichment/overview/#professions-taxonomies">Professions Taxonomy</a> to compare the skill set to. */
    public int ProfessionCodeId;
    /** The language to use for the returned descriptions. */
    public String OutputLanguage;
}