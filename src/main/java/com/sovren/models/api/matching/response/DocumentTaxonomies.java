// Copyright © 2020 Sovren Group, Inc. All rights reserved.
// This file is provided for use by, or on behalf of, Sovren licensees
// within the terms of their license of Sovren products or Sovren customers
// within the Terms of Service pertaining to the Sovren SaaS products.

package com.sovren.models.api.matching.response;

/**
* Primary and secondary taxonomy (industry)
*/
public class DocumentTaxonomies {
    
    /** Best fit taxonomy (industry) evidence.*/
    public TaxonomyEvidence Primary;
    
    /** Second best fit taxonomy (industry) evidence.*/
    public TaxonomyEvidence Secondary;
}
