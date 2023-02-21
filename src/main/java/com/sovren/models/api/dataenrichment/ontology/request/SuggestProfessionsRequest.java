// Copyright © 2020 Sovren Group, Inc. All rights reserved.
// This file is provided for use by, or on behalf of, Sovren licensees
// within the terms of their license of Sovren products or Sovren customers
// within the Terms of Service pertaining to the Sovren SaaS products.

package com.sovren.models.api.dataenrichment.ontology.request;

import java.util.List;

/** Request body for a 'SuggestProfessions' request  */
public class SuggestProfessionsRequest {
    /** The skill IDs used to return the most relevant professions. The list can contain up to 50 skill IDs. */
    public List<String> SkillIds;
    /** Flag to enable returning a list of missing skills per suggested profession. */
    public boolean ReturnMissingSkills = false;
    /** The maximum amount of professions returned. If not specified this parameter defaults to 10. */
    public int Limit = 10;
}