// Copyright © 2023 Textkernel BV. All rights reserved.
// This file is provided for use by, or on behalf of, Textkernel licensees
// within the terms of their license of Textkernel products or Textkernel customers
// within the Terms of Service pertaining to the Textkernel SaaS products.

package com.textkernel.tx.models.api.dataenrichment.ontology.request;

import java.util.List;

/** Request body for a 'SuggestSkills' request  */
public class SuggestSkillsFromProfessionsRequest {
    /** The profession code IDs from the <a href="https://developer.textkernel.com/tx-platform/v10/data-enrichment/overview/#professions-taxonomies">Professions Taxonomy</a> for which the service should return related skills. The list can contain up to 10 profession codes. */
    public List<Integer> ProfessionCodeIds;
    /** The maximum amount of suggested skills returned. If not specified this parameter defaults to 10. This limit cannot exceed 10. */
    public int Limit = 10;
    /** The language to use for the returned descriptions. */
    public String OutputLanguage;
}