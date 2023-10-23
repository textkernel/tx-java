// Copyright © 2020 Sovren Group, Inc. All rights reserved.
// This file is provided for use by, or on behalf of, Sovren licensees
// within the terms of their license of Sovren products or Sovren customers
// within the Terms of Service pertaining to the Sovren SaaS products.

package com.sovren.models.job;

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
    public com.sovren.models.resume.employment.ParsingNormalizedProfession NormalizedProfession;
}
