package com.sovren.models.api.matching.response;

/**
* {@inheritDoc}
*/
public class TaxonomiesScoreData extends CategoryScoreData {
    
    /** Taxonomies/industries found.*/
    public DocumentTaxonomies ActualTaxonomies;
    
    /** Taxonomies/industries requested.*/
    public DocumentTaxonomies DesiredTaxonomies;
}
