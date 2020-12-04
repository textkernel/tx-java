// Copyright © 2020 Sovren Group, Inc. All rights reserved.
// This file is provided for use by, or on behalf of, Sovren licensees
// within the terms of their license of Sovren products or Sovren customers
// within the Terms of Service pertaining to the Sovren SaaS products.

package com.sovren.models.api.matching.request;

import java.util.Optional;

/**
* Filter for a specific skill
*/
public class SkillFilter {
    
    /**
     * The name of the skill. Supports (*, ?) wildcard characters after the third character in the term
     * as defined in https://docs.sovren.com/Documentation/AIMatching#ai-filtering-special-operators
    */
    public String SkillName;
    
    /** The experience level of the skill*/
    public Optional<SkillExperienceLevel> ExperienceLevel;
    
    /** Whether or not the skill must be a current skill*/
    public boolean IsCurrent;
}
