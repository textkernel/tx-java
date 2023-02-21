// Copyright © 2020 Sovren Group, Inc. All rights reserved.
// This file is provided for use by, or on behalf of, Sovren licensees
// within the terms of their license of Sovren products or Sovren customers
// within the Terms of Service pertaining to the Sovren SaaS products.

package com.sovren.models.api.dataenrichment.skills.response;
import java.util.List;

import com.sovren.models.api.dataenrichment.Taxonomy;

/** One entry in the {@link ApiResponse#Value} from a 'GetSkillsTaxonomy' response */
public class GetSkillsTaxonomyResponseValue extends Taxonomy {
    /** A list of skills objects. */
    public List<SkillMultipleDescriptions> Skills;
}
