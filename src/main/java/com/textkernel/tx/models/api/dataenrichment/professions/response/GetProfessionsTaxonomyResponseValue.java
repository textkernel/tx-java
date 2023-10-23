// Copyright © 2023 Textkernel BV. All rights reserved.
// This file is provided for use by, or on behalf of, Textkernel licensees
// within the terms of their license of Sovren products or Textkernel customers
// within the Terms of Service pertaining to the Textkernel SaaS products.

package com.textkernel.tx.models.api.dataenrichment.professions.response;
import java.util.List;
import com.textkernel.tx.models.api.ApiResponse;
import com.textkernel.tx.models.dataenrichment.ProfessionMultipleDescriptions;
import com.textkernel.tx.models.dataenrichment.Taxonomy;

/** One entry in the {@link ApiResponse#Value} from a 'GetProfessionsTaxonomy' response */
public class GetProfessionsTaxonomyResponseValue extends Taxonomy {
    /** A list of returned professions. */
    public List<ProfessionMultipleDescriptions> Professions;
}