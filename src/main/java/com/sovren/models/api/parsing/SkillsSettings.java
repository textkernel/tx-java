package com.sovren.models.api.parsing;

/**
 * Enable skills normalization and enhanced candidate summarization, and specify the version of the skills taxonomy for this parsing transaction.
 */
public class SkillsSettings {
    
    /**
     * When true:
     * <ul>
     *     <li>raw skills will be normalized. These will be output under Value.ResumeData.Skills.Normalized.</li> <a href="https://www.textkernel.com/skills/">Read more</a> about the benefits of using a skills taxonomy.
     *     <li>An enhanced candidate summary is generated, leveraging the taxonomy structure to relate skills to profession groups.</li>
     * </ul>
     * When using TaxonomyVersion V2 (see below), additional <a href="https://www.sovren.com/technical-specs/latest/rest-api/overview/#transaction-cost">charges apply</a>. when normalization is enabled.
     * <br/><br/>
     * When you have access to TaxonomyVersion V1, and did not set the taxonomy to V2 explicitly (see below), normalization is enabled by default and the candidate summary is generated using the V1 taxonomy structure.
     */
    public boolean Normalize;

    /**
     * Specifies the version of the skills taxonomy to use. Defaults to V2, unless your account has access to V1. If you have access to V1, use v2 as the value for this property to explicitly set V2.
     * <br/><br/>
     *  <strong>V1 is deprecated</strong> and will be removed in a future release.
     * <br/><br/>
     * Benefits of V2 include:
     * <ul>
     *     <li>2x larger skills taxonomy, updated frequently based on real-world data</li>
     *     <li>15-40% higher accuracy of extracted skills</li>
     *     <li>Better clustering of skill synonyms</li>
     *     <li>Distinguish skill types (IT / Professional / Soft)</li>
     *     <li>Improved candidate summary</li>
     *     <li>Compatibility with the taxonomy used in Textkernel's <a href="https://www.textkernel.com/solution/data-enrichment-apis/">Data Enrichment APIs</a> and <a href="https://www.jobfeed.com/">Jobfeed</a>, enabling standardization of taxonomies across all of your data and benchmarking against jobs posted online.</li>
     * </ul>
     */
    public String TaxonomyVersion;
}
