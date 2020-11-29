package com.sovren.models.api.matching.response;

/**
* A taxonomy/subtaxonomy (industry/specialization) pair
*/
public class TaxonomyEvidence {
    
    /** Parent taxonomy (industry)*/
    public TaxonomyInfo Taxonomy;
    
    /** Child subtaxonomy (specialization)*/
    public TaxonomyInfo Subtaxonomy;
}
