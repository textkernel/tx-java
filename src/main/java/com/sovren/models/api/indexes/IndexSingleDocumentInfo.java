// Copyright © 2020 Sovren Group, Inc. All rights reserved.
// This file is provided for use by, or on behalf of, Sovren licensees
// within the terms of their license of Sovren products or Sovren customers
// within the Terms of Service pertaining to the Sovren SaaS products.

package com.sovren.models.api.indexes;

/**
* Information for adding a document to an index
*/
public class IndexSingleDocumentInfo extends IndexMultipleDocumentInfo {
    
    /** The id for the index where the document should be added (case-insensitive).*/
    public String IndexId;
}
