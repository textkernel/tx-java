// Copyright © 2023 Textkernel BV. All rights reserved.
// This file is provided for use by, or on behalf of, Textkernel licensees
// within the terms of their license of Textkernel products or Textkernel customers
// within the Terms of Service pertaining to the Textkernel SaaS products.

package com.textkernel.tx.models.api.dataenrichment.ontology.response;
import com.textkernel.tx.models.api.dataenrichment.ontology.request.SuggestProfessionsRequest;

import java.util.List;

/** A profession that was most relevant to the given skill. */
public class SuggestedProfession {
    /** The list of skills relevant to this profession but missing from the given list of skills in the request. This will only be returned if the {@link SuggestProfessionsRequest#ReturnMissingSkills} flag is set to true. */
    public List<SkillScore> MissingSkills;
    /** A value from [0 - 1] indicating how relative the given skills are to this profession. */
    public float Score;
    /** The code ID of the profession in the <a href="https://developer.textkernel.com/tx-platform/v10/data-enrichment/overview/#professions-taxonomies">Professions Taxonomy</a>. */
    public int CodeId;
    /** The description of the profession in the Professions Taxonomy. */
    public String Description;
}