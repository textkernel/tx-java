// Copyright © 2023 Textkernel BV. All rights reserved.
// This file is provided for use by, or on behalf of, Textkernel licensees
// within the terms of their license of Sovren products or Textkernel customers
// within the Terms of Service pertaining to the Textkernel SaaS products.

package com.textkernel.tx.models.api.matching.response;

/**
* A taxonomy/subtaxonomy (industry/specialization) pair
*/
public class TaxonomyEvidence {
    
    /** Parent taxonomy (industry)*/
    public TaxonomyInfo Taxonomy;
    
    /** Child subtaxonomy (specialization)*/
    public TaxonomyInfo Subtaxonomy;
}
