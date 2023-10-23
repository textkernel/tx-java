// Copyright © 2020 Sovren Group, Inc. All rights reserved.
// This file is provided for use by, or on behalf of, Sovren licensees
// within the terms of their license of Sovren products or Sovren customers
// within the Terms of Service pertaining to the Sovren SaaS products.

package com.textkernel.tx.models.resume.skills;

import java.util.List;

/**
* A container for skills taxonomies found in a resume
*/
public class ResumeTaxonomyRoot {

    /** The name of the skills list that these taxonomies belong to*/
    public String Root;

    /** The skills taxonomies found in a resume*/
    public List<ResumeTaxonomy> Taxonomies;
}
