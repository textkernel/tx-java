// Copyright © 2023 Textkernel BV. All rights reserved.
// This file is provided for use by, or on behalf of, Textkernel licensees
// within the terms of their license of Sovren products or Textkernel customers
// within the Terms of Service pertaining to the Textkernel SaaS products.

package com.textkernel.tx.models.api.dataenrichment.professions.request;

import java.util.List;

/** Request body for a 'LookupProfessions' request */
public class LookupProfessionCodesRequest {
    /** The profession code IDs to get details about from the <a href="https://sovren.com/technical-specs/latest/rest-api/data-enrichment/overview/#professions-taxonomies">Professions Taxonomy</a>. */
    public List<Integer> CodeIds;
    /** The language to use for professions descriptions (default is en). Must be an allowed <a href="https://sovren.com/technical-specs/latest/rest-api/data-enrichment/overview/#professions-languages">ISO code</a>. */
    public String OutputLanguage = "en";
}