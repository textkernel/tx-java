// Copyright © 2023 Textkernel BV. All rights reserved.
// This file is provided for use by, or on behalf of, Textkernel licensees
// within the terms of their license of Sovren products or Textkernel customers
// within the Terms of Service pertaining to the Textkernel SaaS products.

package com.textkernel.tx.models.api.matching;

import com.textkernel.tx.models.api.matching.request.SearchMatchRequestBase;
import com.textkernel.tx.models.api.matching.request.PaginationSettings;

/**
* Request body for a search request
*/
public class SearchRequest extends SearchMatchRequestBase {

    /** Used to choose which results to return from the list.*/
    public PaginationSettings PaginationSettings;
}
