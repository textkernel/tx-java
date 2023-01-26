// Copyright © 2020 Sovren Group, Inc. All rights reserved.
// This file is provided for use by, or on behalf of, Sovren licensees
// within the terms of their license of Sovren products or Sovren customers
// within the Terms of Service pertaining to the Sovren SaaS products.

package com.sovren.models.api.dataenrichmentservices.ontology.response;

import java.util.List;

/** One entry in the {@link ApiResponse#Value} from a 'SuggestProfessions' response */
public class SuggestProfessionsResponseValue {
    /** A list of professions most relevant to the given skills. */
    public List<SuggestedProfession> SuggestedProfessions;
}