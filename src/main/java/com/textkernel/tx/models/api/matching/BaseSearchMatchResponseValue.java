// Copyright © 2020 Sovren Group, Inc. All rights reserved.
// This file is provided for use by, or on behalf of, Sovren licensees
// within the terms of their license of Sovren products or Sovren customers
// within the Terms of Service pertaining to the Sovren SaaS products.

package com.textkernel.tx.models.api.matching;

import java.util.List;

/**
* Base class for searches/matches response values
*/
public class BaseSearchMatchResponseValue<T> {

    /** The list of matches for the search/match*/
    public List<T> Matches;

    /** The number of results returned in this response*/
    public int CurrentCount;
    
    /** The total number of results that fit the query/criteria*/
    public int TotalCount;
}
