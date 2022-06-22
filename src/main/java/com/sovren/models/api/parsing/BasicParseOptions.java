// Copyright © 2020 Sovren Group, Inc. All rights reserved.
// This file is provided for use by, or on behalf of, Sovren licensees
// within the terms of their license of Sovren products or Sovren customers
// within the Terms of Service pertaining to the Sovren SaaS products.

package com.sovren.models.api.parsing;

import java.util.List;

/** Basic parse options */
public class BasicParseOptions {

    //********************************
    //IF YOU ADD ANY PARAMS HERE BE SURE TO ADD THEM IN THE DEEP COPY INSIDE ParseRequest.ctor() !!
    //********************************

    /**
     * The configuration settings to use during parsing. See https://sovren.com/technical-specs/latest/rest-api/resume-parser/overview/configuration/#config.
     * <p>NOTE: leaving this null/empty will use the default parsing settings which is recommended in most cases.
     */
    public String Configuration;

    /**
     * If you want to use custom skills lists during parsing, set those here. This not a recommended
     * feature for most customers. For more information, reach out to support@sovren.com
     */
    public List<String> SkillsData;

    /**
     * If you want to use custom normalizations during parsing, set those here. This not a recommended
     * feature for most customers. For more information, reach out to support@sovren.com
     */
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
