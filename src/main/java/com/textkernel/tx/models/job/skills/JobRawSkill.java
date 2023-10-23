package com.textkernel.tx.models.job.skills;

import com.textkernel.tx.models.skills.RawSkill;

/** Skill exactly as it was found in the plain text of the document. */
public class JobRawSkill extends RawSkill {
    /** {@code true} if this skill was listed as 'required' on the job description*/
    public boolean Required;
}
