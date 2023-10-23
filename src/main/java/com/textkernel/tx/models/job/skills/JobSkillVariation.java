// Copyright © 2020 Sovren Group, Inc. All rights reserved.
// This file is provided for use by, or on behalf of, Sovren licensees
// within the terms of their license of Sovren products or Sovren customers
// within the Terms of Service pertaining to the Sovren SaaS products.

package com.sovren.models.job.skills;

import com.sovren.models.skills.Skill;

/**
* A skill listed in a job
*/
public class JobSkillVariation extends Skill {
    
    /** {@code true} if this skill was listed as 'required' on the job description*/
    public boolean Required;
}
