package com.sovren.models.api.parsing;

/**
 * Enable normalization of job titles using our proprietary taxonomy and international standards.
 */
public class ProfessionsSettings {
    
    /**
     * When true, the most recent 3 job titles will be normalized. This includes a proprietary value from our profession taxonomy, plus ONET and ISCO mappings. <a href="https://www.textkernel.com/professions-data-enrichment-api/">Read more</a> about the benefits of using a professions taxonomy.
     * <br/><br/>
     * When enabling professions normalization, additional <a href="https://www.sovren.com/technical-specs/latest/rest-api/overview/#transaction-cost">charges apply</a>.
     * <br/><br/>
     * The following languages are supported: English, Chinese (Simplified), Dutch, French, German, Italian, Polish, Portuguese, and Spanish. For documents in other languages, no normalized values will be returned.
     * <br/><br/>
     * For Sovren AI Matching, normalized professions are automatically indexed and used when profession normalization is enabled during parsing 
     * (through <a href="https://www.sovren.com/technical-specs/latest/rest-api/resume-parser/api/">IndexingOptions</a>). To leverage profession normalization for user-created searches, 
     * enable <a href="https://www.sovren.com/technical-specs/latest/rest-api/ai-matching/querying-api/search/?h=Settings.NormalizeJobTitles">profession normalization at query time</a>.
     * <br/><br/>
     * The profession taxonomy and the mappings are compatible with the taxonomies used in Textkernel's <a href="https://www.textkernel.com/solution/data-enrichment-apis/">Data Enrichment APIs</a> and <a href="https://www.jobfeed.com/">Jobfeed</a>, 
     * enabling standardization of taxonomies across all of your data and benchmarking against jobs posted online.
     */
    public boolean Normalize;
}
