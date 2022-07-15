package com.sovren.models.resume.skills;

import java.time.LocalDate;
import java.util.List;
import com.sovren.models.SovrenPrimitive;
import com.sovren.models.resume.SectionIdentifier;
import com.sovren.models.skills.RawSkill;

/** Skill exactly as it was found in the plain text of the document. */
public class ResumeRawSkill extends RawSkill {
    /** Describes the amount of experience a candidate has with this skill*/
    public SovrenPrimitive<Integer> MonthsExperience;

    /** Describes the date the candidate last used the skill (derived from position dates)*/
    public SovrenPrimitive<LocalDate> LastUsed;

    /** Where the skill was found*/
    public List<SectionIdentifier> FoundIn;
}
