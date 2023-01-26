// Copyright © 2020 Sovren Group, Inc. All rights reserved.
// This file is provided for use by, or on behalf of, Sovren licensees
// within the terms of their license of Sovren products or Sovren customers
// within the Terms of Service pertaining to the Sovren SaaS products.

package com.sovren.models.api.dataenrichmentservices.professions.response;

import java.util.List;

/** One entry in the {@link ApiResponse#Value} from a 'GetProfessionsTaxonomy' response */
public class GetProfessionsTaxonomyResponseValue {
    /** A list of returned professions. */
    public List<ProfessionGroupClass> Professions;
    /** If {@link GetProfessionsTaxonomyRequest#Format} is set to 'csv' on request, this string will contain the csv formatted taxonomy output. */
    public String CsvOutput;
}