// Copyright © 2023 Textkernel BV. All rights reserved.
// This file is provided for use by, or on behalf of, Textkernel licensees
// within the terms of their license of Sovren products or Textkernel customers
// within the Terms of Service pertaining to the Textkernel SaaS products.

package com.textkernel.tx.models.job.skills;

import java.util.List;

/**
* A container for skills taxonomies found in a job
*/
public class JobTaxonomyRoot {

    /** The name of the skills list that these taxonomies belong to*/
    public String Root;

    /** The taxonomies found in a job*/
    public List<JobTaxonomy> Taxonomies;
}
