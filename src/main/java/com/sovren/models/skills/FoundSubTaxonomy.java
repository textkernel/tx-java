package com.sovren.models.skills;

/** 
 * {@inheritDoc} 
 */
public abstract class FoundSubTaxonomy extends SubTaxonomy {

    /** The percent (0-100) of skills found in this subtaxonomy compared to all subtaxonomies */
    public int PercentOfOverall;

    /** The percent (0-100) of skills found in this subtaxonomy compared to other subtaxonomies in the parent taxonomy */
    public int PercentOfParent;
}
