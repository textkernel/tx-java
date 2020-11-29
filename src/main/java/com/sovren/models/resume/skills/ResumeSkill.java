package com.sovren.models.resume.skills;

import java.time.LocalDate;
import java.util.List;

import com.sovren.models.SovrenPrimitive;

/**
* A skill listed in a resume or job
*/
public class ResumeSkill extends ResumeSkillVariation {

    /** The variations (synonyms) of this skill that were found */
    public List<ResumeSkillVariation> Variations;

    /** If this skill has any variations, this describes the total months experience of those variations*/
    public SovrenPrimitive<Integer> ChildrenMonthsExperience;

    /** If this skill has any variations, this describes the most recent date any of the variations were used*/
    public SovrenPrimitive<LocalDate> ChildrenLastUsed;
}
