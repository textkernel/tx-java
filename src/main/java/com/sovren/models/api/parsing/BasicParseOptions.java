package com.sovren.models.api.parsing;

import java.util.List;

/** Basic parse options */
public class BasicParseOptions {

    //********************************
    //IF YOU ADD ANY PARAMS HERE BE SURE TO ADD THEM IN THE DEEP COPY INSIDE ParseRequest.ctor() !!
    //********************************

    /**
     * The configuration settings to use during parsing. See https://docs.sovren.com/#config-string-builder.
     * <p>NOTE: leaving this null/empty will use the default parsing settings which is recommended in most cases.
     */
    public String Configuration;

    /**
     * If you want to use custom skills lists during parsing, set those here. This not a recommended
     * feature for most customers. For more information, see https://docs.sovren.com/#customizing-data-content
     */
    public List<String> SkillsData;

    /**
     * If you want to use custom normalizations during parsing, set those here. This not a recommended
     * feature for most customers. For more information, see https://docs.sovren.com/#customizing-data-content
     */
    public String NormalizerData;

    //********************************
    //IF YOU ADD ANY PARAMS HERE BE SURE TO ADD THEM IN THE DEEP COPY INSIDE ParseRequest.ctor() !!
    //********************************
}
