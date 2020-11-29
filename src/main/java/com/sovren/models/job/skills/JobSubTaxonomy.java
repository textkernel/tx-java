package com.sovren.models.job.skills;

import java.util.List;
import com.sovren.models.skills.FoundSubTaxonomy;

/**
* A subtaxonomy to group similar skills
*/
public class JobSubTaxonomy extends FoundSubTaxonomy {

    /** The skills from this subtaxonomy that were found*/
    public List<JobSkill> Skills;
}
