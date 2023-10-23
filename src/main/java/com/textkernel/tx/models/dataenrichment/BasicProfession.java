package com.textkernel.tx.models.dataenrichment;

/** 
 * A profession ID/description from the Professions Taxonomy.
 */
public class BasicProfession {
    /**
     * The unique code ID of the profession in the <a href="https://developer.textkernel.com/Sovren/v10/data-enrichment/overview/#professions-taxonomies">Professions Taxonomy</a>.
     */
    public Integer CodeId;

    /**
     * The description of the profession in the desired language.
     */
    public String Description;
}