package com.sovren.models.api.matching.response;

/**
* Evidence for a specific taxonomy/subtaxonomy
*/
public class TaxonomyInfo {
    
    /** Taxonomy/subtaxonomy name*/
    public String Name;
    
    /** Id for the taxonomy/subtaxonomy*/
    public String Id;
    
    /** {@code true} when this taxonomy/subtaxonomy is found in both source and target documents*/
    public boolean Matched;
}
