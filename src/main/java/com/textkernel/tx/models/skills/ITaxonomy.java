// Copyright © 2020 Sovren Group, Inc. All rights reserved.
// This file is provided for use by, or on behalf of, Sovren licensees
// within the terms of their license of Sovren products or Sovren customers
// within the Terms of Service pertaining to the Sovren SaaS products.

package com.sovren.models.skills;

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
