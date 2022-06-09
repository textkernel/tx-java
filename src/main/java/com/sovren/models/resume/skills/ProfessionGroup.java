package com.sovren.models.resume.skills;

import java.util.List;

/** Profession Group that describes a percentage of the Profession Class. */
public class ProfessionGroup {

    
        /** Name of the profession group. */
        public String Name;

        /** Id of the profession group. */
        public String Id;

        /** Precent of parent described by this profession group. All values under a parent will add up to 100%. */
        public double Percent;

        /** Array of normalized skills associated to this profession group. */
        public List<String> NormalizedSkills;
}
