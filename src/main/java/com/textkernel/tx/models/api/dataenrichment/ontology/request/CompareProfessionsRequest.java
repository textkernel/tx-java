// Copyright © 2020 Sovren Group, Inc. All rights reserved.
// This file is provided for use by, or on behalf of, Sovren licensees
// within the terms of their license of Sovren products or Sovren customers
// within the Terms of Service pertaining to the Sovren SaaS products.

package com.textkernel.tx.models.api.dataenrichment.ontology.request;

/** Request body for a 'CompareProfessions' request */
public class CompareProfessionsRequest {
    /**  A profession code ID from the <a href="https://sovren.com/technical-specs/latest/rest-api/data-enrichment/overview/#professions-taxonomies">Professions Taxonomy</a> to compare. */
    public int ProfessionACodeId;
    /**  A profession code ID from the <a href="https://sovren.com/technical-specs/latest/rest-api/data-enrichment/overview/#professions-taxonomies">Professions Taxonomy</a> to compare. */
    public int ProfessionBCodeId;
    /** The language to use for the returned descriptions. */
    public String OutputLanguage;
}