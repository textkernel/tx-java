// Copyright © 2023 Textkernel BV. All rights reserved.
// This file is provided for use by, or on behalf of, Textkernel licensees
// within the terms of their license of Textkernel products or Textkernel customers
// within the Terms of Service pertaining to the Textkernel SaaS products.

package com.textkernel.tx.models.dataenrichment;

import java.time.LocalDate;

/** Metadata about the Skills or Professions taxonomies */
public class TaxonomyMetadata {
    /** The version number of the professions service. */
    public String ServiceVersion;
    /** The date the taxonomy was released. */
    public LocalDate TaxonomyReleaseDate;
}
