// Copyright © 2020 Sovren Group, Inc. All rights reserved.
// This file is provided for use by, or on behalf of, Sovren licensees
// within the terms of their license of Sovren products or Sovren customers
// within the Terms of Service pertaining to the Sovren SaaS products.

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
