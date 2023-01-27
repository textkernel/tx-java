// Copyright © 2020 Sovren Group, Inc. All rights reserved.
// This file is provided for use by, or on behalf of, Sovren licensees
// within the terms of their license of Sovren products or Sovren customers
// within the Terms of Service pertaining to the Sovren SaaS products.

package com.sovren.models.api.dataenrichmentservices.skills.response;

import java.time.LocalDate;

/** One entry in the {@link ApiResponse#Value} from a 'GetSkillsMetadata' response */
public class GetSkillsMetadataResponseValue {
    /** The version number of the skills service. */
    public String ServiceVersion;
    /** The date the taxonomy was released. */
    public LocalDate TaxonomyReleaseDate;
}
