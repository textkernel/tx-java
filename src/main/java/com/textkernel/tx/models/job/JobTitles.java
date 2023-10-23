// Copyright © 2023 Textkernel BV. All rights reserved.
// This file is provided for use by, or on behalf of, Textkernel licensees
// within the terms of their license of Sovren products or Textkernel customers
// within the Terms of Service pertaining to the Textkernel SaaS products.

package com.textkernel.tx.models.job;

import java.util.List;

/**
* Job titles found in a job description
*/
public class JobTitles {
    
    /** The main/overall job title*/
    public String MainJobTitle;
    
    /** All job titles found in the job description*/
    public List<String> JobTitle;

    /** Normalized profession for the main job title.*/
    public com.textkernel.tx.models.resume.employment.ParsingNormalizedProfession NormalizedProfession;
}
