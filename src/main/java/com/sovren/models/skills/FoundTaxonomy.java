// Copyright © 2020 Sovren Group, Inc. All rights reserved.
// This file is provided for use by, or on behalf of, Sovren licensees
// within the terms of their license of Sovren products or Sovren customers
// within the Terms of Service pertaining to the Sovren SaaS products.

package com.sovren.models.skills;

/** A container to group similar skills subtaxonomies (see {@link SubTaxonomy})*/
public abstract class FoundTaxonomy<T> extends ITaxonomy<T> {

    /** The percent (0-100) of skills found in the document that belong to this taxonomy */
    public int PercentOfOverall;
}
