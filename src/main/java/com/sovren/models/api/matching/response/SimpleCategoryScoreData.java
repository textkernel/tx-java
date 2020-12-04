// Copyright © 2020 Sovren Group, Inc. All rights reserved.
// This file is provided for use by, or on behalf of, Sovren licensees
// within the terms of their license of Sovren products or Sovren customers
// within the Terms of Service pertaining to the Sovren SaaS products.

package com.sovren.models.api.matching.response;

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
