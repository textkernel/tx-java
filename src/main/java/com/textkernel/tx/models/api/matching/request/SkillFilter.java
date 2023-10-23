// Copyright © 2023 Textkernel BV. All rights reserved.
// This file is provided for use by, or on behalf of, Textkernel licensees
// within the terms of their license of Sovren products or Textkernel customers
// within the Terms of Service pertaining to the Textkernel SaaS products.

package com.textkernel.tx.models.api.matching.request;

/**
* Filter for a specific skill
*/
public class SkillFilter {
    
    /**
     * The name of the skill. Supports (*, ?) wildcard characters after the third character in the term
     * as defined in https://sovren.com/technical-specs/latest/rest-api/ai-matching/overview/querying/#ai-filtering-special-operators
    */
    public String SkillName;
    
    /** The experience level of the skill*/
    public SkillExperienceLevel ExperienceLevel;
    
    /** Whether or not the skill must be a current skill*/
    public boolean IsCurrent;
}
