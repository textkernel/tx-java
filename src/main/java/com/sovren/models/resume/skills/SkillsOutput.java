package com.sovren.models.resume.skills;

import java.util.List;

/** Skills output when version 2 of the taxonomy is utilized. */
public class SkillsOutput {
    
        /** Array of skills exactly as found in the plain text of the document. */
        public List<RawSkill> Raw;

        /** Normalized skills output when version 2 of the taxonomy is utilized and SkillsSettings.Normalize is set to true. */
        public List<NormalizedSkill> Normalized;

        /** Professions most related to the document. Only output if version 2 of the skills taxonomy is utilized and SkillsSettings.Normalize is set to true. */
        public List<ProfessionClass> RelatedProfessionClasses;
}
