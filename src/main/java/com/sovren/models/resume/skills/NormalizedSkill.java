package com.sovren.models.resume.skills;

import java.time.LocalDate;
import java.util.List;

import com.sovren.models.SovrenPrimitive;

/** Normalized skill concept representing one or more raw skills that were extracted. */
public class NormalizedSkill {
    
        /** Name of the normalized skill.*/
        public String Name;

        /** Id of this skill in the skills taxonomy. */
        public String Id;

        /** Type of skill. Possible values are Professional, IT, or Soft. */
        public String Type;

        /** Describes the amount of experience a candidate has with this skill. Null if unknown. */
        public SovrenPrimitive<Integer> MonthsExperience;

        /** Describes the date the candidate last used the skill (derived from position dates). Null if unknown. */
        public SovrenPrimitive<LocalDate> LastUsed;

        /** Array of objects denoting where in the document this skill was located. */
        public List<FoundIn> FoundIn;

        /** Array of raw skills that were extracted that normalized to this skill. */
        public List<String> RawSkills;
}
