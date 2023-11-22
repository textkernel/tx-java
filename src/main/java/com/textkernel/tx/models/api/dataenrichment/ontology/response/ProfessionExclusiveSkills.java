package com.textkernel.tx.models.api.dataenrichment.ontology.response;

import java.util.List;

/** An exclusive skill per profession. */
public class ProfessionExclusiveSkills {
    /** The code ID of the profession in the <a href="https://developer.textkernel.com/tx-platform/v10/data-enrichment/overview/#professions-taxonomies">Professions Taxonomy</a>. */
    public int ProfessionCodeId;
    /** A list of skills that are relative to this profession, but not the other. */
    public List<SkillScore> SkillsFoundOnlyInThisProfession;
}
