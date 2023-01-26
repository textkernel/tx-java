// Copyright © 2020 Sovren Group, Inc. All rights reserved.
// This file is provided for use by, or on behalf of, Sovren licensees
// within the terms of their license of Sovren products or Sovren customers
// within the Terms of Service pertaining to the Sovren SaaS products.

package com.sovren.models.api.dataenrichmentservices.professions.request;

import java.util.List;

/** Request body for a 'LookupProfessions' request */
public class LookupProfessionCodesRequest {
    /** The profession code IDs to get details about from the <a href="https://sovren.com/technical-specs/latest/rest-api/data-enrichment-services/overview/#professions-taxonomies">Sovren Professions Taxonomy</a>. */
    public List<Integer> CodeIds;
    /** The language to use for professions descriptions (default is en). Must be an allowed <a href="https://sovren.com/technical-specs/latest/rest-api/data-enrichment-services/overview/#professions-languages">ISO code</a>. */
    public String OutputLanguage = "en";
}