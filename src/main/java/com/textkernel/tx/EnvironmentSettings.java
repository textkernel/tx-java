// Copyright © 2023 Textkernel BV. All rights reserved.
// This file is provided for use by, or on behalf of, Textkernel licensees
// within the terms of their license of Textkernel products or Textkernel customers
// within the Terms of Service pertaining to the Textkernel SaaS products.

package com.textkernel.tx;

import com.textkernel.tx.models.api.matchV2.MatchV2Environment;

/** Settings to configure things that would have an effect across many API endpoints */
public class EnvironmentSettings {  
    /**
     * Should certification skills be included when using the Skills Intelligence APIs
     */
    public boolean SkillsIntelligenceIncludeCertifications = true;
    
    /**
     * The environment to target for any SearchMatchV2 API calls
     */
    public MatchV2Environment MatchV2Environment = com.textkernel.tx.models.api.matchV2.MatchV2Environment.ACC;

    /**
     * The Data Center for your account. Either {@link DataCenter#US}, {@link DataCenter#EU}, or {@link DataCenter#AU}.
     * Found at https://cloud.textkernel.com/tx/console
     */
    public DataCenter DataCenter;
}
