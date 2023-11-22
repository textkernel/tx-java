// Copyright © 2023 Textkernel BV. All rights reserved.
// This file is provided for use by, or on behalf of, Textkernel licensees
// within the terms of their license of Textkernel products or Textkernel customers
// within the Terms of Service pertaining to the Textkernel SaaS products.

package com.textkernel.tx.models.api.parsing;

import java.util.List;

/** Basic parse options */
public class BasicParseOptions {

    //********************************
    //IF YOU ADD ANY PARAMS HERE BE SURE TO ADD THEM IN THE DEEP COPY INSIDE ParseRequest.ctor() !!
    //********************************

    /**
     * The configuration settings to use during parsing. See https://developer.textkernel.com/tx-platform/v10/resume-parser/overview/configuration/#config.
     * <p>NOTE: leaving this null/empty will use the default parsing settings which is recommended in most cases.
     */
    public String Configuration;

    /**
     * If you want to use custom skills lists during parsing, set those here. This not a recommended
     * feature for most customers. For more information, reach out to service@textkernel.com
     * @deprecated use {@link #SkillsSettings} instead
     */
    @Deprecated
    public List<String> SkillsData;

    /**
     * If you want to use custom normalizations during parsing, set those here. This not a recommended
     * feature for most customers. For more information, reach out to service@textkernel.com
     * @deprecated use {@link #SkillsSettings} and {@link #ProfessionsSettings} instead
     */
    @Deprecated
    public String NormalizerData;

    /**
    * Enable skills normalization and enhanced candidate summarization, and specify the version of the skills taxonomy for this parsing transaction.
    */
    public SkillsSettings SkillsSettings;

    /**
    * Enable normalization of job titles using our proprietary taxonomy and international standards.
    */
    public ProfessionsSettings ProfessionsSettings;

    //********************************
    //IF YOU ADD ANY PARAMS HERE BE SURE TO ADD THEM IN THE DEEP COPY INSIDE ParseRequest.ctor() !!
    //********************************
}
