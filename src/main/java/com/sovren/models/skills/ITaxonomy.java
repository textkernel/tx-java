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
