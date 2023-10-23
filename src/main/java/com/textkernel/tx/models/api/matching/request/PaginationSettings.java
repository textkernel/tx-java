// Copyright © 2020 Sovren Group, Inc. All rights reserved.
// This file is provided for use by, or on behalf of, Sovren licensees
// within the terms of their license of Sovren products or Sovren customers
// within the Terms of Service pertaining to the Sovren SaaS products.

package com.textkernel.tx.models.api.matching.request;

/**
* Settings for pagination of results
*/
public class PaginationSettings {
    
    /** How many results to return*/
    public Integer Take;
    
    /** How many results to skip. For example: (skip 5, take 10) means return results 6-15*/
    public Integer Skip;
}
