// Copyright © 2020 Sovren Group, Inc. All rights reserved.
// This file is provided for use by, or on behalf of, Sovren licensees
// within the terms of their license of Sovren products or Sovren customers
// within the Terms of Service pertaining to the Sovren SaaS products.

package com.sovren.models.api.dataenrichment.professions.response;
import java.util.List;
import com.sovren.models.api.ApiResponse;
import com.sovren.models.dataenrichment.ProfessionMultipleDescriptions;
import com.sovren.models.dataenrichment.Taxonomy;

/** One entry in the {@link ApiResponse#Value} from a 'GetProfessionsTaxonomy' response */
public class GetProfessionsTaxonomyResponseValue extends Taxonomy {
    /** A list of returned professions. */
    public List<ProfessionMultipleDescriptions> Professions;
}