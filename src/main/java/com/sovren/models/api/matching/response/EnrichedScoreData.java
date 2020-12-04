// Copyright © 2020 Sovren Group, Inc. All rights reserved.
// This file is provided for use by, or on behalf of, Sovren licensees
// within the terms of their license of Sovren products or Sovren customers
// within the Terms of Service pertaining to the Sovren SaaS products.

package com.sovren.models.api.matching.response;

/**
* Contains metadata/evidence about scores for a specific match/score result
*/
public class EnrichedScoreData {
    
    /** Detailed match information for the Languages category.*/
    public SimpleCategoryScoreData Languages;
    
    /** Detailed match information for the Certifications category.*/
    public SimpleCategoryScoreData Certifications;
    
    /** Detailed match information for the ExecutiveType category.*/
    public SimpleCategoryScoreData ExecutiveType;
    
    /** Detailed match information for the Education category.*/
    public EducationScoreData Education;
    
    /** Detailed match information for the Taxonomies category.*/
    public TaxonomiesScoreData Taxonomies;
    
    /** Detailed match information for the JobTitles category.*/
    public JobTitlesScoreData JobTitles;
    
    /** Detailed match information for the Skills category.*/
    public SkillsScoreData Skills;
    
    /** Detailed match information for the ManagementLevel category.*/
    public ManagementLevelScoreData ManagementLevel;
}
