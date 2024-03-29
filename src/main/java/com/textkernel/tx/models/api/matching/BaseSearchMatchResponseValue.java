// Copyright © 2023 Textkernel BV. All rights reserved.
// This file is provided for use by, or on behalf of, Textkernel licensees
// within the terms of their license of Textkernel products or Textkernel customers
// within the Terms of Service pertaining to the Textkernel SaaS products.

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
