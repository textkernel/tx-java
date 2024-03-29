package com.textkernel.tx.models.job.skills;

import com.textkernel.tx.models.skills.NormalizedSkill;

/** Normalized skill concept representing one or more raw skills that were extracted. */
public class JobNormalizedSkill extends NormalizedSkill {
    /** {@code true} if this skill was listed as 'required' on the job description*/
    public boolean Required;
}
