// Copyright © 2023 Textkernel BV. All rights reserved.
// This file is provided for use by, or on behalf of, Textkernel licensees
// within the terms of their license of Textkernel products or Textkernel customers
// within the Terms of Service pertaining to the Textkernel SaaS products.

package com.textkernel.tx.models.api.matching.response;

/**
* Primary and secondary taxonomy (industry)
*/
public class DocumentTaxonomies {
    
    /** Best fit taxonomy (industry) evidence.*/
    public TaxonomyEvidence Primary;
    
    /** Second best fit taxonomy (industry) evidence.*/
    public TaxonomyEvidence Secondary;
}
