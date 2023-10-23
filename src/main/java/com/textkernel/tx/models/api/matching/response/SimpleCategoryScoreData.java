// Copyright © 2023 Textkernel BV. All rights reserved.
// This file is provided for use by, or on behalf of, Textkernel licensees
// within the terms of their license of Sovren products or Textkernel customers
// within the Terms of Service pertaining to the Textkernel SaaS products.

package com.textkernel.tx.models.api.matching.response;

import java.util.List;

/**
* Details about the score for a specific category
*/
public class SimpleCategoryScoreData extends CategoryScoreData {
    
    /** List of terms found in both source and target documents*/
    public List<String> Found;
    
    /** List of terms requested but not found*/
    public List<String> NotFound;
}
