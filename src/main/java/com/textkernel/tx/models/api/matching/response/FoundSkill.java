// Copyright © 2023 Textkernel BV. All rights reserved.
// This file is provided for use by, or on behalf of, Textkernel licensees
// within the terms of their license of Textkernel products or Textkernel customers
// within the Terms of Service pertaining to the Textkernel SaaS products.

package com.textkernel.tx.models.api.matching.response;

/**
* Information about a skill match
*/
public class FoundSkill {
    
    /** Name of the skill found.*/
    public String Skill;
    
    /** {@code true} when the skill is found in the current time-frame.*/
    public boolean IsCurrent;
}
