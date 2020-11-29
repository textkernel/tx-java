package com.sovren.models.api.matching.response;

/**
* Information about a skill match
*/
public class FoundSkill {
    
    /** Name of the skill found.*/
    public String Skill;
    
    /** {@code true} when the skill is found in the current time-frame.*/
    public boolean IsCurrent;
}
