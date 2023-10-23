// Copyright © 2023 Textkernel BV. All rights reserved.
// This file is provided for use by, or on behalf of, Textkernel licensees
// within the terms of their license of Sovren products or Textkernel customers
// within the Terms of Service pertaining to the Textkernel SaaS products.

package com.textkernel.tx.models.api.indexes;

/**
* Information for adding a document to an index
*/
public class IndexSingleDocumentInfo extends IndexMultipleDocumentInfo {
    
    /** The id for the index where the document should be added (case-insensitive).*/
    public String IndexId;
}
