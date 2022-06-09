// Copyright © 2020 Sovren Group, Inc. All rights reserved.
// This file is provided for use by, or on behalf of, Sovren licensees
// within the terms of their license of Sovren products or Sovren customers
// within the Terms of Service pertaining to the Sovren SaaS products.

package com.sovren.models.resume.employment;

import java.util.List;
import com.sovren.models.SovrenDate;
import com.sovren.models.SovrenPrimitive;

/**
* A position/job on a resume
*/
public class Position {
    
    /** The id of this position (one-based, so POS-1 is the first, etc)*/
    public String Id;
    
    /** The employer/company for this position. Will be {@code null} if {@link #IsSelfEmployed} is {@code true}*/
    public Employer Employer;
    
    /** A list of {@link #Id}s that have overlapping dates with this {@link Position}*/
    public List<String> RelatedToByDates;
    
    /** A list of {@link #Id}s that have the same {@link Employer} as this {@link Position}*/
    public List<String> RelatedToByCompanyName;
    
    /** {@code true} if the candidate was self-employed at this job/position*/
    public boolean IsSelfEmployed;
    
    /** {@code true} if the job/position is listed as current on the resume*/
    public boolean IsCurrent;
    
    /** The job title for this position/job*/
    public JobTitle JobTitle;
    
    /** The start date listed for this position*/
    public SovrenDate StartDate;
    
    /** The end date listed for this position*/
    public SovrenDate EndDate;
    
    /** How many employees were supervised in this position/job*/
    public SovrenPrimitive<Integer> NumberEmployeesSupervised;
    
    /**
     * The type of job. One of:
     * <ul>
     * <li>directHire</li>
     * <li>contract</li>
     * <li>temp</li>
     * <li>volunteer</li>
     * <li>internship</li>
     * <li>UNSPECIFIED</li>
     * </ul>
    */
    public String JobType;
    
    /** The name of the skills taxonomy that this position was categorized as based on skills found in the job description.*/
    public String TaxonomyName;
    
    /** The name of the skills subtaxonomy that this position was categorized as based on skills found in the job description.*/
    public String SubTaxonomyName;
    
    /**
     * The level determined by length of experience and job titles. One of:
     * <ul>
     * <li>Entry Level</li>
     * <li>Experienced(non-manager)</li>
     * <li>Senior(more than 5 years experience)</li>
     * <li>Manager</li>
     * <li>Senior Manager(more than 5 years management experience)</li>
     * <li>Executive(VP, Dept.Head)</li>
     * <li>Senior Executive(President, C-level)</li>
     * </ul>
    */
    public String JobLevel;

    /** The percentage of this job described by the {@link #TaxonomyName} */
    public int TaxonomyPercentage;
    
    /** The job description*/
    public String Description;
    
    /** Bullet points found in the {@link #Description} (available when {@code OutputFormat.CreateBullets = true} is set in the Configuration string on the request)*/
    public List<Bullet> Bullets;

    /** Normalized profession of the {@link #JobTitle} */
    public NormalizedProfession NormalizedProfession;
}
