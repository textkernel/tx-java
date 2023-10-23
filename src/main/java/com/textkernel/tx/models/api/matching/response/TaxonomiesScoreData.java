// Copyright © 2020 Sovren Group, Inc. All rights reserved.
// This file is provided for use by, or on behalf of, Sovren licensees
// within the terms of their license of Sovren products or Sovren customers
// within the Terms of Service pertaining to the Sovren SaaS products.

package com.sovren.models.api.matching.response;

/**
 * Details about the score for the Taxonomies category
 */
public class TaxonomiesScoreData extends CategoryScoreData {
    
    /** Taxonomies/industries found.*/
    public DocumentTaxonomies ActualTaxonomies;
    
    /** Taxonomies/industries requested.*/
    public DocumentTaxonomies DesiredTaxonomies;
}
