// Copyright © 2023 Textkernel BV. All rights reserved.
// This file is provided for use by, or on behalf of, Textkernel licensees
// within the terms of their license of Sovren products or Textkernel customers
// within the Terms of Service pertaining to the Textkernel SaaS products.

package com.textkernel.tx.models.resume.skills;

import java.util.List;
import com.textkernel.tx.models.skills.FoundSubTaxonomy;

/**
* A subtaxonomy to group similar skills
*/
public class ResumeSubTaxonomy extends FoundSubTaxonomy {

    /** The skills from this subtaxonomy that were found*/
    public List<ResumeSkill> Skills;
}
