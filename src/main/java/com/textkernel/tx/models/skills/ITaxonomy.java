// Copyright © 2023 Textkernel BV. All rights reserved.
// This file is provided for use by, or on behalf of, Textkernel licensees
// within the terms of their license of Sovren products or Textkernel customers
// within the Terms of Service pertaining to the Textkernel SaaS products.

package com.textkernel.tx.models.skills;

import java.util.List;

/** A container to group similar skills subtaxonomies (see {@link SubTaxonomy})*/
public abstract class ITaxonomy<T> {
    
    /** The id of the skills taxonomy*/
    public String Id;

    /** The human-readable name*/
    public String Name;

    /** The subtaxonomy children of this taxonomy (more specific groupings of skills)*/
    public List<T> SubTaxonomies;
}
