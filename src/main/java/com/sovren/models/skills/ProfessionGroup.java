package com.sovren.models.skills;

import java.util.List;

/** Profession Group that describes a percentage of the Profession Class. */
public class ProfessionGroup {

    
        /** Name of the profession group. */
        public String Name;

        /** Id of the profession group. */
        public String Id;

        /** Percent of overall document described by this profession group. All groups across all classes will add up to 100%.*/
        public int Percent;

        /** Array of normalized skills associated to this profession group. */
        public List<String> NormalizedSkills;
}
