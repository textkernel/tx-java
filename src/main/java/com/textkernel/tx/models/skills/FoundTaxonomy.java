// Copyright © 2023 Textkernel BV. All rights reserved.
// This file is provided for use by, or on behalf of, Textkernel licensees
// within the terms of their license of Textkernel products or Textkernel customers
// within the Terms of Service pertaining to the Textkernel SaaS products.

package com.textkernel.tx.models.skills;

/** A container to group similar skills subtaxonomies (see {@link SubTaxonomy})*/
public abstract class FoundTaxonomy<T> extends ITaxonomy<T> {

    /** The percent (0-100) of skills found in the document that belong to this taxonomy */
    public int PercentOfOverall;
}
