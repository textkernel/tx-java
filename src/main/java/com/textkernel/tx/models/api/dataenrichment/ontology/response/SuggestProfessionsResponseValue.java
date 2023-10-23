// Copyright © 2023 Textkernel BV. All rights reserved.
// This file is provided for use by, or on behalf of, Textkernel licensees
// within the terms of their license of Sovren products or Textkernel customers
// within the Terms of Service pertaining to the Textkernel SaaS products.

package com.textkernel.tx.models.api.dataenrichment.ontology.response;
import com.textkernel.tx.models.api.ApiResponse;

import java.util.List;

/** One entry in the {@link ApiResponse#Value} from a 'SuggestProfessions' response */
public class SuggestProfessionsResponseValue {
    /** A list of professions most relevant to the given skills. */
    public List<SuggestedProfession> SuggestedProfessions;
    /** Any warnings when attempting to suggest professions from the given skills. */
    public SuggestedProfessionsWarnings Warnings;
}