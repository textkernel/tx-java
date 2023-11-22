package com.textkernel.tx.models.api.parsing;

import com.textkernel.tx.models.resume.skills.ResumeV2Skills;
import com.textkernel.tx.models.job.skills.JobV2Skills;

/**
 * Enable skills normalization and enhanced candidate summarization, and specify the version of the skills taxonomy for this parsing transaction.
 */
public class SkillsSettings {
    
    /**
     * When {@code true}:
     * <ul>
     *     <li>Raw skills will be normalized. These will be output under {@link ResumeV2Skills#Normalized} or {@link JobV2Skills#Normalized}.
     *     <li>An enhanced candidate summary is generated, leveraging the taxonomy structure to relate skills with profession groups.</li>
     *     <li>When {@link #TaxonomyVersion} is set to (or defaults to) {@code V2}, <a href="https://developer.textkernel.com/tx-platform/v10/overview/#transaction-cost">additional charges apply</a></li>
     * </ul>
     * <p>
     * <b>This setting has no effect when {@link #TaxonomyVersion} is set to (or defaults to) {@code V1}.</b>
     */
    public boolean Normalize;

    /**
     * Specifies the version of the skills taxonomy to use. One of:
     * <ul>
     *  <li>{@code V1} - <b>(DEPRECATED)</b> This is the default for old accounts. Will be removed in a future release.</li>
     *  <li>{@code V2} - This is the default for new accounts, and must be explicitly set if you have access to V1 and V2.</li>
     * </ul>
     * <p>
     * Benefits of V2 include:
     * <ul>
     *     <li>2x larger skills taxonomy, updated frequently based on real-world data.</li>
     *     <li>15-40% higher accuracy of extracted skills.</li>
     *     <li>Better clustering of skill synonyms.</li>
     *     <li>Distinguish skill types (IT / Professional / Soft).</li>
     *     <li>Improved candidate summary.</li>
     *     <li>Compatibility with the taxonomy used in Textkernel's <a href="https://www.textkernel.com/solution/data-enrichment-apis/">Data Enrichment APIs</a> and <a href="https://www.jobfeed.com/">Jobfeed</a>, enabling standardization of taxonomies across all of your data and benchmarking against jobs posted online.</li>
     * </ul>
     */
    public String TaxonomyVersion;
}
