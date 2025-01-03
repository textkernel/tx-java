package com.textkernel.tx.models.dataenrichment;

/** A skill from the Skills Taxonomy */
public class Skill {
    /** The description of the skill in the requested language. */
    public String Description;
    /** The ID of the skill in the taxonomy. */
    public String Id;
    /** Type of skill. Possible values are Professional, IT, Language, Soft, or Certification (only when using v2 endpoints). */
    public String Type;
    /** The language ISO 639-1 code. This will only appear for language skills (Type = Language). */
    public String IsoCode;
}
