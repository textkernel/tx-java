package com.sovren.models.resume.skills;

import java.util.List;
import com.sovren.models.skills.FoundSubTaxonomy;

/**
* A subtaxonomy to group similar skills
*/
public class ResumeSubTaxonomy extends FoundSubTaxonomy {

    /** The skills from this subtaxonomy that were found*/
    public List<ResumeSkill> Skills;
}
