package com.sovren.models.api.dataenrichmentservices.skills.response;

/** A skill from the skills taxonomy. */
public class SkillCode {
    /** The ID of the skill in the skills taxonomy. */
    public String Id;
    /** The skill description in the requested language. */
    public String Description;
    /** Type of skill. Possible values are Professional, IT, Language, or Soft. */
    public String Type;
}
