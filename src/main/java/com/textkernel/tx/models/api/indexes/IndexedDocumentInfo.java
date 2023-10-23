// Copyright © 2023 Textkernel BV. All rights reserved.
// This file is provided for use by, or on behalf of, Textkernel licensees
// within the terms of their license of Textkernel products or Textkernel customers
// within the Terms of Service pertaining to the Textkernel SaaS products.

package com.textkernel.tx.models.api.indexes;

/**
* A small container for identifying an indexed document
*/
public class IndexedDocumentInfo {
    
    /** The id for the index that contains the document (case-insensitive).*/
    public String IndexId;
    
    /** The id of the document (case-insensitive)*/
    public String DocumentId;
}
