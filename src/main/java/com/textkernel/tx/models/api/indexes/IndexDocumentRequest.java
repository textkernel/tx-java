// Copyright © 2023 Textkernel BV. All rights reserved.
// This file is provided for use by, or on behalf of, Textkernel licensees
// within the terms of their license of Textkernel products or Textkernel customers
// within the Terms of Service pertaining to the Textkernel SaaS products.

package com.textkernel.tx.models.api.indexes;

import java.util.List;

/** Base class for 'index resume' or 'index job' request body */
public class IndexDocumentRequest {
    
    /** The user-defined tags the document should have */
    public List<String> UserDefinedTags;
}
