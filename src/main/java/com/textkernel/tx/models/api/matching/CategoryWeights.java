// Copyright © 2023 Textkernel BV. All rights reserved.
// This file is provided for use by, or on behalf of, Textkernel licensees
// within the terms of their license of Sovren products or Textkernel customers
// within the Terms of Service pertaining to the Textkernel SaaS products.

package com.textkernel.tx.models.api.matching;

/**
* Weights for each category used in matching/scoring
*/
public class CategoryWeights {
    
    /** The weight of the Education category relative to other categories.*/
    public double Education;
    
    /** The weight of the JobTitles category relative to other categories.*/
    public double JobTitles;
    
    /** The weight of the Skills category relative to other categories.*/
    public double Skills;
    
    /** The weight of the Industries/Taxonomies category relative to other categories.*/
    public double Industries;
    
    /** The weight of the Languages category relative to other categories.*/
    public double Languages;
    
    /** The weight of the Certifications category relative to other categories.*/
    public double Certifications;
    
    /** The weight of the ExecutiveType category relative to other categories.*/
    public double ExecutiveType;
    
    /** The weight of the ManagementLevel category relative to other categories.*/
    public double ManagementLevel;
    
    /** If {@code false}, the Education category has no data and should be ignored/hidden.*/
    public boolean EducationHasData;
    
    /** If {@code false}, the JobTitles category has no data and should be ignored/hidden.*/
    public boolean JobTitlesHasData;
    
    /** If {@code false}, the Skills category has no data and should be ignored/hidden.*/
    public boolean SkillsHasData;
    
    /** If {@code false}, the Industries/Taxonomies category has no data and should be ignored/hidden.*/
    public boolean IndustriesHasData;
    
    /** If {@code false}, the Languages category has no data and should be ignored/hidden.*/
    public boolean LanguagesHasData;
    
    /** If {@code false}, the Certifications category has no data and should be ignored/hidden.*/
    public boolean CertificationsHasData;
    
    /** If {@code false}, the ExecutiveType category has no data and should be ignored/hidden.*/
    public boolean ExecutiveTypeHasData;
    
    /** If {@code false}, the ManagementLevel category has no data and should be ignored/hidden.*/
    public boolean ManagementLevelHasData;
}
