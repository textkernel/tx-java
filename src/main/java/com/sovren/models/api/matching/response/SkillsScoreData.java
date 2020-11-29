package com.sovren.models.api.matching.response;

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
