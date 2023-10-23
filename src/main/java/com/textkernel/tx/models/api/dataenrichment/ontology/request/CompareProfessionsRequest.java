// Copyright © 2023 Textkernel BV. All rights reserved.
// This file is provided for use by, or on behalf of, Textkernel licensees
// within the terms of their license of Sovren products or Textkernel customers
// within the Terms of Service pertaining to the Textkernel SaaS products.

package com.textkernel.tx.models.api.dataenrichment.ontology.request;

/** Request body for a 'CompareProfessions' request */
public class CompareProfessionsRequest {
    /**  A profession code ID from the <a href="https://developer.textkernel.com/Sovren/v10/data-enrichment/overview/#professions-taxonomies">Professions Taxonomy</a> to compare. */
    public int ProfessionACodeId;
    /**  A profession code ID from the <a href="https://developer.textkernel.com/Sovren/v10/data-enrichment/overview/#professions-taxonomies">Professions Taxonomy</a> to compare. */
    public int ProfessionBCodeId;
    /** The language to use for the returned descriptions. */
    public String OutputLanguage;
}