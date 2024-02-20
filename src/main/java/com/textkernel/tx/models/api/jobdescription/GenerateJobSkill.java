// Copyright © 2023 Textkernel BV. All rights reserved.
// This file is provided for use by, or on behalf of, Textkernel licensees
// within the terms of their license of Textkernel products or Textkernel customers
// within the Terms of Service pertaining to the Textkernel SaaS products.

package com.textkernel.tx.models.api.jobdescription;

/*A skill used when generating a job via assistants*/
public class GenerateJobSkill {
    /** The name of the skill */
    public String Name;
    /** Priority of the skill */
    public SkillPriority Priority;
    
}
