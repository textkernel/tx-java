// Copyright © 2023 Textkernel BV. All rights reserved.
// This file is provided for use by, or on behalf of, Textkernel licensees
// within the terms of their license of Textkernel products or Textkernel customers
// within the Terms of Service pertaining to the Textkernel SaaS products.

package com.textkernel.tx.models.api.indexes;

import java.util.List;

/**
 * Request body to delete multiple indexed documents
 */
public class DeleteMultipleDocumentsRequest {
    
    /** The document IDs to delete */
    public List<String> DocumentIds;
}
