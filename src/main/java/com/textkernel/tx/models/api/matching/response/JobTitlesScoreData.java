// Copyright © 2020 Sovren Group, Inc. All rights reserved.
// This file is provided for use by, or on behalf of, Sovren licensees
// within the terms of their license of Sovren products or Sovren customers
// within the Terms of Service pertaining to the Sovren SaaS products.

package com.textkernel.tx.models.api.matching.response;

import java.util.List;

/**
 * Details about the score for the Job Titles category
 */
public class JobTitlesScoreData extends CategoryScoreData {
    
    /** List of terms requested, but not found.*/
    public List<String> NotFound;
    
    /** List of job titles found in both documents.*/
    public List<FoundJobTitle> Found;
}
