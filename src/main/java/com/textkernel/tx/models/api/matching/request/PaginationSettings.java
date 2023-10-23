// Copyright © 2023 Textkernel BV. All rights reserved.
// This file is provided for use by, or on behalf of, Textkernel licensees
// within the terms of their license of Sovren products or Textkernel customers
// within the Terms of Service pertaining to the Textkernel SaaS products.

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
