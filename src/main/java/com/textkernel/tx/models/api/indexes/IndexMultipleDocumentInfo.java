// Copyright © 2023 Textkernel BV. All rights reserved.
// This file is provided for use by, or on behalf of, Textkernel licensees
// within the terms of their license of Textkernel products or Textkernel customers
// within the Terms of Service pertaining to the Textkernel SaaS products.

package com.textkernel.tx.models.api.indexes;

import java.util.List;

/**
 * Information for adding a single document to an index as part of a 'batch upload'
 */
public class IndexMultipleDocumentInfo {
    
    /**
     * The id to assign to the new document. This is restricted to alphanumeric with dashes and underscores. 
     * All values will be converted to lower-case.
    */
    public String DocumentId;

    /** The user-defined tags the document should have */
    public List<String> UserDefinedTags;
}
