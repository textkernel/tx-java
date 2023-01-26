// Copyright © 2020 Sovren Group, Inc. All rights reserved.
// This file is provided for use by, or on behalf of, Sovren licensees
// within the terms of their license of Sovren products or Sovren customers
// within the Terms of Service pertaining to the Sovren SaaS products.

package com.sovren.models.api.dataenrichmentservices.ontology.request;

import java.util.List;

/** Request body for a 'CompareSkillsToProfession' request */
public class CompareSkillsToProfessionRequest {
    /** The skill IDs which should be compared against the given profession. The list can contain up to 50 skills.  */
    public List<String> SkillIds;
    /** The profession code ID from the <a href="https://sovren.com/technical-specs/latest/rest-api/data-enrichment-services/overview/#professions-taxonomies">Sovren Professions Taxonomy</a> to compare the skill set to. */
    public int ProfessionCodeId;
}