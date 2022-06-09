package com.sovren.models.resume.skills;

import java.time.LocalDate;
import java.util.List;
import com.sovren.models.SovrenPrimitive;

public class RawSkill {
    /// <summary>
        /// The exact skill text extracted from the document.
        /// </summary>
        public String Name;

        /// <summary>
        /// Describes the amount of experience a candidate has with this skill. Null if unknown.
        /// </summary>
        public SovrenPrimitive<Integer> MonthsExperience;

        /// <summary>
        /// Describes the date the candidate last used the skill (derived from position dates). Null if unknown.
        /// </summary>
        public SovrenPrimitive<LocalDate> LastUsed;

        /// <summary>
        /// Array of objects denoting where in the document this skill was located.
        /// </summary>
        public List<FoundIn> FoundIn;
}
