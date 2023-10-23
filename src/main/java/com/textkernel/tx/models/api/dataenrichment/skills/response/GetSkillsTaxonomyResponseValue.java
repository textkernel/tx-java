// Copyright © 2023 Textkernel BV. All rights reserved.
// This file is provided for use by, or on behalf of, Textkernel licensees
// within the terms of their license of Sovren products or Textkernel customers
// within the Terms of Service pertaining to the Textkernel SaaS products.

package com.textkernel.tx.models.api.dataenrichment.skills.response;
import java.util.List;
import com.textkernel.tx.models.api.ApiResponse;
import com.textkernel.tx.models.dataenrichment.SkillMultipleDescriptions;
import com.textkernel.tx.models.dataenrichment.Taxonomy;

/** One entry in the {@link ApiResponse#Value} from a 'GetSkillsTaxonomy' response */
public class GetSkillsTaxonomyResponseValue extends Taxonomy {
    /** A list of skills objects. */
    public List<SkillMultipleDescriptions> Skills;
}
