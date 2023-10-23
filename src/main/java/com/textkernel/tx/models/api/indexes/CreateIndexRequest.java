// Copyright © 2020 Sovren Group, Inc. All rights reserved.
// This file is provided for use by, or on behalf of, Sovren licensees
// within the terms of their license of Sovren products or Sovren customers
// within the Terms of Service pertaining to the Sovren SaaS products.

package com.sovren.models.api.indexes;

import com.sovren.models.matching.IndexType;

/**
* Request body to create an index
*/
public class CreateIndexRequest {

    /** The type of documents this index will contain*/
    public IndexType IndexType;
}
