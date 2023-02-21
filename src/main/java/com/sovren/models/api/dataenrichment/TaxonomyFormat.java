package com.sovren.models.api.dataenrichment;

/**
*  The format used to retrieve the DES skills or professions taxonomy
*/
public enum TaxonomyFormat {
    /**
    *  Retrieves JSON, and all the response properties will be populated except the {@link Taxonomy#CsvOutput}
    */
    json, 
    
    /**
    *  Retrieves CSV, and the only response property populated will be the {@link Taxonomy#CsvOutput}
    */
    csv
}
