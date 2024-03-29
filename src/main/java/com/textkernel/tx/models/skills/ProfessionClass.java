package com.textkernel.tx.models.skills;

import java.util.List;

/** Profession Class that describes a percentage of the source document. */
public class ProfessionClass {
    
        /** Name of the related profession. */
        public String Name;

        /** Id of the related profession. */
        public String Id;

        /** Percent of overall document that relates to this profession. */
        public int Percent;

        /** Array of objects representing groups of professions. */
        public List<ProfessionGroup> Groups;
}
