// Copyright © 2020 Sovren Group, Inc. All rights reserved.
// This file is provided for use by, or on behalf of, Sovren licensees
// within the terms of their license of Sovren products or Sovren customers
// within the Terms of Service pertaining to the Sovren SaaS products.

package com.textkernel.tx.models.api.matching.response;

import java.util.List;

/**
* Details about the score for a specific category
*/
public class CategoryScoreData {
    
    /** An unweighted score from 0-100. This is the percentage match of this category.*/
    public double UnweightedScore;
    
    /** Detailed written explanation about each data point found or not found.*/
    public List<CategoryScoreEvidence> Evidence;
}
