// Copyright © 2023 Textkernel BV. All rights reserved.
// This file is provided for use by, or on behalf of, Textkernel licensees
// within the terms of their license of Textkernel products or Textkernel customers
// within the Terms of Service pertaining to the Textkernel SaaS products.

package com.textkernel.tx.models.job.skills;

import java.util.List;

/**
* A skill listed in a job
*/
public class JobSkill extends JobSkillVariation {

    /** The variations (synonyms) of this skill that were found*/
    public List<JobSkillVariation> Variations;
}
