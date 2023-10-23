// Copyright © 2023 Textkernel BV. All rights reserved.
// This file is provided for use by, or on behalf of, Textkernel licensees
// within the terms of their license of Textkernel products or Textkernel customers
// within the Terms of Service pertaining to the Textkernel SaaS products.

package com.textkernel.tx.models.api.matching.response;

/**
* Evidence for a specific taxonomy/subtaxonomy
*/
public class TaxonomyInfo {
    
    /** Taxonomy/subtaxonomy name*/
    public String Name;
    
    /** Id for the taxonomy/subtaxonomy*/
    public String Id;
    
    /** {@code true} when this taxonomy/subtaxonomy is found in both source and target documents*/
    public boolean Matched;
}
