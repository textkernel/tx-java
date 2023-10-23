package com.textkernel.tx.models.api.dataenrichment;
import com.textkernel.tx.models.dataenrichment.Taxonomy;

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
