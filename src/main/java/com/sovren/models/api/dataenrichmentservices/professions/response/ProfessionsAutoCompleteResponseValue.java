// Copyright © 2020 Sovren Group, Inc. All rights reserved.
// This file is provided for use by, or on behalf of, Sovren licensees
// within the terms of their license of Sovren products or Sovren customers
// within the Terms of Service pertaining to the Sovren SaaS products.

package com.sovren.models.api.dataenrichmentservices.professions.response;

import java.util.List;

/** One entry in the {@link ApiResponse#Value} from a 'ProfessionsAutocomplete' response */
public class ProfessionsAutoCompleteResponseValue {
    /** A list of professions that match the given Prefix. */
    public List<AutoCompleteProfession> Professions;
}