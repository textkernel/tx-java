package com.sovren.models.skills;

/** 
 * {@inheritDoc} 
 */
public abstract class FoundTaxonomy<T> extends ITaxonomy<T> {

    /** The percent (0-100) of skills found in the document that belong to this taxonomy */
    public int PercentOfOverall;
}
