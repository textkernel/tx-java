// Copyright © 2020 Sovren Group, Inc. All rights reserved.
// This file is provided for use by, or on behalf of, Sovren licensees
// within the terms of their license of Sovren products or Sovren customers
// within the Terms of Service pertaining to the Sovren SaaS products.

package com.sovren.models.api.matching;

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
}
