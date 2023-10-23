// Copyright © 2020 Sovren Group, Inc. All rights reserved.
// This file is provided for use by, or on behalf of, Sovren licensees
// within the terms of their license of Sovren products or Sovren customers
// within the Terms of Service pertaining to the Sovren SaaS products.

package com.textkernel.tx.models.resume.skills;

import com.textkernel.tx.models.resume.SectionIdentifier;
import com.textkernel.tx.models.skills.Skill;
import com.textkernel.tx.models.SovrenPrimitive;

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
