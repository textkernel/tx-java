// Copyright © 2020 Sovren Group, Inc. All rights reserved.
// This file is provided for use by, or on behalf of, Sovren licensees
// within the terms of their license of Sovren products or Sovren customers
// within the Terms of Service pertaining to the Sovren SaaS products.

package com.sovren.models.api.dataenrichment.ontology.request;

import java.util.List;

/** Request body for a 'SuggestSkills' request  */
public class SuggestSkillsFromProfessionsRequest {
    /** The profession code IDs from the <a href="https://sovren.com/technical-specs/latest/rest-api/data-enrichment/overview/#professions-taxonomies">Professions Taxonomy</a> for which the service should return related skills. The list can contain up to 10 profession codes. */
    public List<Integer> ProfessionCodeIds;
    /** The maximum amount of suggested skills returned. If not specified this parameter defaults to 10. This limit cannot exceed 10. */
    public int Limit = 10;
}