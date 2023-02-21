package com.sovren.models.api.dataenrichment.professions.response;

/** A profession object that matched the given Prefix. */
public class AutoCompleteProfession {
    /** The description of the found profession in the requested language. */
    public String Description;
    /** The code ID of this profession in the <a href="https://sovren.com/technical-specs/latest/rest-api/data-enrichment-services/overview/#professions-taxonomies">Sovren Professions Taxonomy</a>. */
    public int CodeId;
}
