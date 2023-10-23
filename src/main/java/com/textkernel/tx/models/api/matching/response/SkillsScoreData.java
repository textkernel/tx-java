// Copyright © 2023 Textkernel BV. All rights reserved.
// This file is provided for use by, or on behalf of, Textkernel licensees
// within the terms of their license of Textkernel products or Textkernel customers
// within the Terms of Service pertaining to the Textkernel SaaS products.

package com.textkernel.tx.models.api.matching.response;

import java.util.List;

/**
 * Details about the score for the Skills category
 */
public class SkillsScoreData extends CategoryScoreData {
    
    /** List of terms requested, but not found.*/
    public List<String> NotFound;
    
    /** List of skills found in both source and target documents*/
    public List<FoundSkill> Found;
}
