// Copyright © 2020 Sovren Group, Inc. All rights reserved.
// This file is provided for use by, or on behalf of, Sovren licensees
// within the terms of their license of Sovren products or Sovren customers
// within the Terms of Service pertaining to the Sovren SaaS products.

package com.sovren.models.api.matching;

import com.sovren.models.api.matching.request.SearchMatchRequestBase;
import com.sovren.models.api.matching.request.PaginationSettings;

/**
* Request body for a search request
*/
public class SearchRequest extends SearchMatchRequestBase {

    /** Used to choose which results to return from the list.*/
    public PaginationSettings PaginationSettings;
}
