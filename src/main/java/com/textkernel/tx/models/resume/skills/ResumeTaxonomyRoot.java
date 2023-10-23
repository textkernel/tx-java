// Copyright © 2023 Textkernel BV. All rights reserved.
// This file is provided for use by, or on behalf of, Textkernel licensees
// within the terms of their license of Textkernel products or Textkernel customers
// within the Terms of Service pertaining to the Textkernel SaaS products.

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
