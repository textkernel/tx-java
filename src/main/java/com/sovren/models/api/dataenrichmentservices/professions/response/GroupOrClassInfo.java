package com.sovren.models.api.dataenrichmentservices.professions.response;

import java.util.List;

/** The group or class info. */
public class GroupOrClassInfo<T> {
    /** The code ID of the item in the <a href="https://sovren.com/technical-specs/latest/rest-api/data-enrichment-services/overview/#professions-taxonomies">Sovren Professions Taxonomy</a>. */
    public T CodeId;
    /** The description(s) of the item in the desired language(s). */
    public List<LangDescription> Descriptions;
}
