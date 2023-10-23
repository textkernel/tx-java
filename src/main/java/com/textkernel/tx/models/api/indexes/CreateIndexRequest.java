// Copyright © 2023 Textkernel BV. All rights reserved.
// This file is provided for use by, or on behalf of, Textkernel licensees
// within the terms of their license of Sovren products or Textkernel customers
// within the Terms of Service pertaining to the Textkernel SaaS products.

package com.textkernel.tx.models.api.indexes;

import com.textkernel.tx.models.matching.IndexType;

/**
* Request body to create an index
*/
public class CreateIndexRequest {

    /** The type of documents this index will contain*/
    public IndexType IndexType;
}
