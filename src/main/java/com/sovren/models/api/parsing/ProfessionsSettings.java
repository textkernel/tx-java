package com.sovren.models.api.parsing;

import com.sovren.models.api.matching.request.SearchMatchSettings;

/**
 * Enable normalization of job titles using our proprietary taxonomy and international standards.
 */
public class ProfessionsSettings {
    
    /**
     * When true, the most recent 3 job titles will be normalized. This includes a proprietary value from our profession taxonomy, plus ONET and ISCO mappings. <a href="https://www.textkernel.com/professions-data-enrichment-api/">Read more</a> about the benefits of using a professions taxonomy.
     * <p>
     * When enabling professions normalization, <a href="https://www.sovren.com/technical-specs/latest/rest-api/overview/#transaction-cost">additional charges apply</a>.
     * <p>
     * The following languages are supported: English, Chinese (Simplified), Dutch, French, German, Italian, Polish, Portuguese, and Spanish. For documents in other languages, no normalized values will be returned.
     * <p>
     * For Sovren AI Matching, normalized professions are automatically indexed and used if enabled. To also leverage profession 
     * normalization for user-created searches, enable {@link SearchMatchSettings#NormalizeJobTitles}.
     * <p>
     * The profession taxonomy and the mappings are compatible with the taxonomies used in Textkernel's <a href="https://www.textkernel.com/solution/data-enrichment-apis/">Data Enrichment APIs</a> and <a href="https://www.jobfeed.com/">Jobfeed</a>, 
     * enabling standardization of taxonomies across all of your data and benchmarking against jobs posted online.
     */
    public boolean Normalize;
}
