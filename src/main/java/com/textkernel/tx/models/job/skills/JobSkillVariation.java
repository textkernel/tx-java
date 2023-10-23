// Copyright © 2023 Textkernel BV. All rights reserved.
// This file is provided for use by, or on behalf of, Textkernel licensees
// within the terms of their license of Textkernel products or Textkernel customers
// within the Terms of Service pertaining to the Textkernel SaaS products.

package com.textkernel.tx.models.job.skills;

import com.textkernel.tx.models.skills.Skill;

/**
* A skill listed in a job
*/
public class JobSkillVariation extends Skill {
    
    /** {@code true} if this skill was listed as 'required' on the job description*/
    public boolean Required;
}
