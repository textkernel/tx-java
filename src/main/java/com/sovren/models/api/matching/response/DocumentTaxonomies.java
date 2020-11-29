package com.sovren.models.api.matching.response;

/**
* Primary and secondary taxonomy (industry)
*/
public class DocumentTaxonomies {
    
    /** Best fit taxonomy (industry) evidence.*/
    public TaxonomyEvidence Primary;
    
    /** Second best fit taxonomy (industry) evidence.*/
    public TaxonomyEvidence Secondary;
}
