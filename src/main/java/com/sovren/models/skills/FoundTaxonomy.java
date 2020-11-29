package com.sovren.models.skills;

/** A container to group similar skills subtaxonomies (see {@link SubTaxonomy})*/
public abstract class FoundTaxonomy<T> extends ITaxonomy<T> {

    /** The percent (0-100) of skills found in the document that belong to this taxonomy */
    public int PercentOfOverall;
}
