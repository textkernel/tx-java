package com.sovren.models.resume.skills;

import com.sovren.models.resume.SectionIdentifier;
import com.sovren.models.skills.Skill;
import com.sovren.models.SovrenPrimitive;

import java.time.LocalDate;
import java.util.List;

/**
* A skill listed in a resume or job
*/
public class ResumeSkillVariation extends Skill {

    /** Describes the amount of experience a candidate has with this skill*/
    public SovrenPrimitive<Integer> MonthsExperience;

    /** Describes the date the candidate last used the skill (derived from position dates)*/
    public SovrenPrimitive<LocalDate> LastUsed;

    /** Where the skill was found*/
    public List<SectionIdentifier> FoundIn;
}
