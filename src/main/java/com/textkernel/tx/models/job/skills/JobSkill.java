// Copyright © 2020 Sovren Group, Inc. All rights reserved.
// This file is provided for use by, or on behalf of, Sovren licensees
// within the terms of their license of Sovren products or Sovren customers
// within the Terms of Service pertaining to the Sovren SaaS products.

package com.textkernel.tx.models.job.skills;

import java.util.List;

/**
* A skill listed in a job
*/
public class JobSkill extends JobSkillVariation {

    /** The variations (synonyms) of this skill that were found*/
    public List<JobSkillVariation> Variations;
}
