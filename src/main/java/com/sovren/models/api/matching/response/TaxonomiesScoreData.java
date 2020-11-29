package com.sovren.models.api.matching.response;

/**
 * Details about the score for the Taxonomies category
 */
public class TaxonomiesScoreData extends CategoryScoreData {
    
    /** Taxonomies/industries found.*/
    public DocumentTaxonomies ActualTaxonomies;
    
    /** Taxonomies/industries requested.*/
    public DocumentTaxonomies DesiredTaxonomies;
}
