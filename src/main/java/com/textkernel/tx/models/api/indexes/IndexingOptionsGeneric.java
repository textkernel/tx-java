// Copyright © 2023 Textkernel BV. All rights reserved.
// This file is provided for use by, or on behalf of, Textkernel licensees
// within the terms of their license of Textkernel products or Textkernel customers
// within the Terms of Service pertaining to the Textkernel SaaS products.

package com.textkernel.tx.models.api.indexes;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.textkernel.tx.models.api.matchV2.MatchV2Environment;

/**
 * Generic options that have properties for both V1 and V2.
 */
public class IndexingOptionsGeneric {
    
    /**
     * The Search &amp; Match Version to use for indexing.
     */
    public SearchAndMatchVersion SearchAndMatchVersion;

    /**
     * The id to assign to the new document. This is restricted to alphanumeric with dashes and underscores. 
     * All values will be converted to lower-case.
    */
    public String DocumentId;

    /**
     * The id for the index where the document should be added (case-insensitive).
     * <p>
     * Only use when {@link #SearchAndMatchVersion} = {@link SearchAndMatchVersion#V1}
     * </p>
    */
    public String IndexId;

    /** The user-defined tags the document should have
     * <p>
     * Only use when {@link #SearchAndMatchVersion} = {@link SearchAndMatchVersion#V1}
     * </p>
    */
    public List<String> UserDefinedTags;

    /**
     * The target environment where the document will be uploaded
     * <p>
     * Only use when {@link #SearchAndMatchVersion} = {@link SearchAndMatchVersion#V2}
     * </p>
     */
    public MatchV2Environment SearchAndMatchEnvironment;

    /**
     * The list of roles that are allowed to retrieve the document. If not set, <code>["all"]</code> will be used.
     * <p>
     * Only use when {@link #SearchAndMatchVersion} = {@link SearchAndMatchVersion#V2}
     * </p>
     */
    public List<String> Roles;

    /**
     * A key-value collection of custom fields.
     * <p>
     * Only use when {@link #SearchAndMatchVersion} = {@link SearchAndMatchVersion#V2}
     * </p>
     */
    public Map<String, String> CustomFields;

    /** <strong>Be sure to set all the correct properties depending if you are using Match V1 or V2 if you use this constructor</strong> */
    IndexingOptionsGeneric() { }

    /**
     * Create options to index a document with Match V1
     * @param documentId The id to assign to the new document. This is restricted to alphanumeric with dashes and underscores. All values will be converted to lower-case.
     * @param indexId The id for the index where the document should be added (case-insensitive).
     * @param userDefinedTags (optional) The user-defined tags the document should have
     */
    public IndexingOptionsGeneric(String documentId, String indexId, List<String> userDefinedTags) {
        SearchAndMatchVersion = com.textkernel.tx.models.api.indexes.SearchAndMatchVersion.V1;
        UserDefinedTags = userDefinedTags != null ? userDefinedTags : new ArrayList<>();
        IndexId = indexId;
        DocumentId = documentId;
    }

    /**
     * Create options to upload a document with Match V2
     * @param env The target environment where the document will be uploaded
     * @param documentId The id to assign to the new document. This is restricted to alphanumeric with dashes and underscores.
     * @param roles (optional) The list of roles that are allowed to retrieve the document. If not set, <code>["all"]</code> will be used.
     * @param customFields (optional) A key-value collection of custom fields.
     */
    public IndexingOptionsGeneric(
            MatchV2Environment env,
            String documentId,
            List<String> roles,
            Map<String, String> customFields) {
        SearchAndMatchVersion = com.textkernel.tx.models.api.indexes.SearchAndMatchVersion.V2;
        Roles = roles;
        CustomFields = customFields;
        DocumentId = documentId;
        SearchAndMatchEnvironment = env;
    }
}
