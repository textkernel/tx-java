package com.sovren.models.api.dataenrichment;

/**
*  Base class for Skills and Professions taxonomies
*/
public abstract class Taxonomy {
    /**
    *  If {@link Taxonomy#CsvOutput} is requested, this string will contain the csv formatted taxonomy output.
    */
    public String CsvOutput;
}
