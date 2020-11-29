package com.sovren.models.skills;

/**
* A skill listed in a resume or job
*/
public abstract class Skill {

    /** The Id of the skill*/
    public String Id;

    /** The name of the skill*/
    public String Name;

    /** Where the skill was found*/
    public String FoundIn;

    /** Whether or not this skill was found verbatim in the text*/
    public boolean ExistsInText;
}
