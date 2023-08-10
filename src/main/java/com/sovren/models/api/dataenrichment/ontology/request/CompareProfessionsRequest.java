// Copyright © 2020 Sovren Group, Inc. All rights reserved.
// This file is provided for use by, or on behalf of, Sovren licensees
// within the terms of their license of Sovren products or Sovren customers
// within the Terms of Service pertaining to the Sovren SaaS products.

package com.sovren.models.api.dataenrichment.ontology.request;

import java.util.List;

/** Request body for a 'CompareProfessions' request */
public class CompareProfessionsRequest {
    /**  The two profession code IDs from the <a href="https://sovren.com/technical-specs/latest/rest-api/data-enrichment/overview/#professions-taxonomies">Professions Taxonomy</a> to compare. This list must have 2 values. */
    public List<Integer> ProfessionCodeIds;
}