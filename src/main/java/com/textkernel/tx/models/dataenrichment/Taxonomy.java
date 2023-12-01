package com.textkernel.tx.models.dataenrichment;
import com.textkernel.tx.models.api.dataenrichment.TaxonomyFormat;

/**
*  Base class for Skills and Professions taxonomies
*/
public abstract class Taxonomy {
    /**
    *  If {@link TaxonomyFormat#csv} is requested, this string will contain the csv formatted taxonomy output.
    */
    public String CsvOutput;
}
