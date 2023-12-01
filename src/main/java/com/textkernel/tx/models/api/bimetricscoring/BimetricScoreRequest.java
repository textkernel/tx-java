// Copyright © 2023 Textkernel BV. All rights reserved.
// This file is provided for use by, or on behalf of, Textkernel licensees
// within the terms of their license of Textkernel products or Textkernel customers
// within the Terms of Service pertaining to the Textkernel SaaS products.

package com.textkernel.tx.models.api.bimetricscoring;

import java.util.List;
import com.textkernel.tx.models.api.matching.request.SearchMatchSettings;
import com.textkernel.tx.models.api.matching.BaseScoredResponseValue;
import com.textkernel.tx.models.api.matching.CategoryWeights;

/**
* Request body for a 'BimetricScore' request
*/
public class BimetricScoreRequest {
    
    /** The settings to use during scoring calculations*/
    public SearchMatchSettings Settings;
    
    /** 
     * The weights you want to use for scoring. <b>It is important to specify these, otherwise default values will be used. </b>
     * <p>These weights will be used except in the case
     * that you provided a non-zero weight for a category that is irrelevant in the source document.
     * For example, this can happen when the source document contains no languages.
     * <p>See also: {@link BaseScoredResponseValue#AppliedCategoryWeights}
    */
    public CategoryWeights PreferredCategoryWeights;
    
    /** 
     * A list of parsed resumes containing the document name/identifier and the parsed resume data.
     * NOTE: you can only use either this property to score resumes, or {@link #TargetJobs} to score jobs, but not both
    */
    public List<ParsedResumeWithId> TargetResumes;
    
    /** 
     * A list of parsed jobs containing the document name/identifier and the parsed job data.
     * NOTE: you can only use either this property to score jobs, or {@link #TargetResumes} to score resumes, but not both
    */
    public List<ParsedJobWithId> TargetJobs;
}
