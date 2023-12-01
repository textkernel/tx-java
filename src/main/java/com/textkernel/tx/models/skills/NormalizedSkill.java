package com.textkernel.tx.models.skills;

import java.util.List;

/** Normalized skill concept representing one or more raw skills that were extracted. */
public class NormalizedSkill {
    /** Name of the normalized skill.*/
    public String Name;

    /** Id of this skill in the skills taxonomy. */
    public String Id;

    /** Type of skill. Possible values are Professional, IT, or Soft. */
    public String Type;

    /** Array of raw skills that were extracted that normalized to this skill. */
    public List<String> RawSkills;
}
