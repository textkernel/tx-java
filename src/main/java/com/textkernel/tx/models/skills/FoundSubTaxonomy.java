// Copyright © 2023 Textkernel BV. All rights reserved.
// This file is provided for use by, or on behalf of, Textkernel licensees
// within the terms of their license of Sovren products or Textkernel customers
// within the Terms of Service pertaining to the Textkernel SaaS products.

package com.textkernel.tx.models.skills;

/**
* A subtaxonomy to group similar skills
*/
public abstract class FoundSubTaxonomy extends SubTaxonomy {

    /** The percent (0-100) of skills found in this subtaxonomy compared to all subtaxonomies */
    public int PercentOfOverall;

    /** The percent (0-100) of skills found in this subtaxonomy compared to other subtaxonomies in the parent taxonomy */
    public int PercentOfParent;
}
