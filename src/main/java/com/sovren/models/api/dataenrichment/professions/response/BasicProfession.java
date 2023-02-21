package com.sovren.models.api.dataenrichment.professions.response;

/** 
 * A profession ID/description from the Sovren Professions Taxonomy.
 */
public class BasicProfession {
    /**
     * The unique code ID of the profession in the <a href="https://sovren.com/technical-specs/latest/rest-api/data-enrichment-services/overview/#professions-taxonomies">Sovren Professions Taxonomy</a>.
     */
    public Integer CodeId;

    /**
     * The description of the profession in the desired language.
     */
    public String Description;
}