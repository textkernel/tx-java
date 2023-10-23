// Copyright © 2020 Sovren Group, Inc. All rights reserved.
// This file is provided for use by, or on behalf of, Sovren licensees
// within the terms of their license of Sovren products or Sovren customers
// within the Terms of Service pertaining to the Sovren SaaS products.

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
