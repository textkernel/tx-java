// Copyright © 2023 Textkernel BV. All rights reserved.
// This file is provided for use by, or on behalf of, Textkernel licensees
// within the terms of their license of Textkernel products or Textkernel customers
// within the Terms of Service pertaining to the Textkernel SaaS products.

package com.textkernel.tx.models.job.skills;

import java.util.List;
import com.textkernel.tx.models.skills.FoundSubTaxonomy;

/**
* A subtaxonomy to group similar skills
*/
public class JobSubTaxonomy extends FoundSubTaxonomy {

    /** The skills from this subtaxonomy that were found*/
    public List<JobSkill> Skills;
}
