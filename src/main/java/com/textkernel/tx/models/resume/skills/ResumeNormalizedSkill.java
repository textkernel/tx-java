package com.sovren.models.resume.skills;

import java.time.LocalDate;
import java.util.List;

import com.sovren.models.SovrenPrimitive;
import com.sovren.models.resume.SectionIdentifier;
import com.sovren.models.skills.NormalizedSkill;

/** Normalized skill concept representing one or more raw skills that were extracted. */
public class ResumeNormalizedSkill extends NormalizedSkill {
    /** Describes the amount of experience a candidate has with this skill*/
    public SovrenPrimitive<Integer> MonthsExperience;

    /** Describes the date the candidate last used the skill (derived from position dates)*/
    public SovrenPrimitive<LocalDate> LastUsed;

    /** Where the skill was found*/
    public List<SectionIdentifier> FoundIn;
}
