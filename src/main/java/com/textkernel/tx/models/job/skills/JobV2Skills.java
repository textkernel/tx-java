package com.textkernel.tx.models.job.skills;

import java.util.List;
import com.textkernel.tx.models.skills.ProfessionClass;
import com.textkernel.tx.models.api.parsing.SkillsSettings;

/** Skills output when version 2 of the taxonomy is utilized. */
public class JobV2Skills {
    
        /** Array of skills exactly as found in the plain text of the document. */
        public List<JobRawSkill> Raw;

        /** Normalized skills output when {@link SkillsSettings#TaxonomyVersion} is set to (or defaults to) {@code V2} 
         * and {@link SkillsSettings#Normalize} is set to {@code true} */
        public List<JobNormalizedSkill> Normalized;

        /** Professions most related to the document. Only output when {@link SkillsSettings#TaxonomyVersion} is set to (or defaults to) {@code V2} 
         * and {@link SkillsSettings#Normalize} is set to {@code true} */
        public List<ProfessionClass> RelatedProfessionClasses;
}
