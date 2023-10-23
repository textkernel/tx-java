// Copyright © 2023 Textkernel BV. All rights reserved.
// This file is provided for use by, or on behalf of, Textkernel licensees
// within the terms of their license of Sovren products or Textkernel customers
// within the Terms of Service pertaining to the Textkernel SaaS products.

package com.textkernel.tx.models.api.matching.response;

/**
* Details about the score for the Management Level category
*/
public class ManagementLevelScoreData extends CategoryScoreData {
    
    /** Actual management level found.*/
    public String Actual;
    
    /** Requested management level.*/
    public String Desired;
    
    /** {@code true} when the duration of management experience matches in the source and target documents.*/
    public boolean AmountOfExperienceMatches;
}
