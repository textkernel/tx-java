// Copyright © 2023 Textkernel BV. All rights reserved.
// This file is provided for use by, or on behalf of, Textkernel licensees
// within the terms of their license of Sovren products or Textkernel customers
// within the Terms of Service pertaining to the Textkernel SaaS products.

package com.textkernel.tx.models.api.matching.request;

import java.util.List;
import com.textkernel.tx.models.skills.Taxonomy;
import com.textkernel.tx.models.resume.employment.ExperienceSummary;

/**
* Criteria for filtering search/match results
*/
public class FilterCriteria {
    
    /** When specified, the revision date of documents must fall within this range.*/
    public RevisionDateRange RevisionDateRange;
    
    /** Results must have one of the specified document ids (case-insensitive).*/
    public List<String> DocumentIds;
    
    /** List of user-defined tags. Either all or at least one are required depending on the value of {@link #UserDefinedTagsMustAllExist}*/
    public List<String> UserDefinedTags;
    
    /**
     * When {@code true}, all of the user-defined tags in {@link #UserDefinedTags} must be found. 
     * By default, this is {@code false}, which means that at least one of the {@link #UserDefinedTags} must be found.
    */
    public boolean UserDefinedTagsMustAllExist;
    
    /** Use to filter results based on location.*/
    public LocationCriteria LocationCriteria;
    
    /**
     * Full-text boolean semantic expresion. For full details on the syntax and supported 
     * operations refer to https://developer.textkernel.com/Sovren/v10/ai-matching/overview/querying/#ai-filtering-fulltext
    */
    public String SearchExpression;
    
    /** If {@code true}, results must have/require patent experience.*/
    public boolean HasPatents;
    
    /** If {@code true}, results must have/require security credentials.*/
    public boolean HasSecurityCredentials;
    
    /**
     * Results must have/require at least one of the security credentials specified.
     * Supports (*, ?) wildcard characters after the third character in the term as defined 
     * in https://developer.textkernel.com/Sovren/v10/ai-matching/overview/querying/#ai-filtering-special-operators
    */
    public List<String> SecurityCredentials;
    
    /** If {@code true}, results must have/require experience as an author.*/
    public boolean IsAuthor;
    
    /** If {@code true}, results must have/require public speaking experience.*/
    public boolean IsPublicSpeaker;
    
    /** If {@code true}, results must have/require military experience.*/
    public boolean IsMilitary;
    
    /**
     * Results must have at least one of the specified school names. Supports (*, ?) wildcard 
     * characters after the third character in the term as defined in
     * https://developer.textkernel.com/Sovren/v10/ai-matching/overview/querying/#ai-filtering-special-operators
    */
    public List<String> SchoolNames;
    
    /**
     * Results must have at least one of the specified degree names. Supports (*, ?) wildcard 
     * characters after the third character in the term as defined in
     * hhttps://developer.textkernel.com/Sovren/v10/ai-matching/overview/querying/#ai-filtering-special-operators
    */
    public List<String> DegreeNames;
    
    /**
     * Results must have at least one of the specified degree types. One of:
     * <ul>
     * <li>specialeducation</li>
     * <li>someHighSchoolOrEquivalent</li>
     * <li>ged</li>
     * <li>secondary</li>
     * <li>highSchoolOrEquivalent</li>
     * <li>certification</li>
     * <li>vocational</li>
     * <li>someCollege</li>
     * <li>HND_HNC_OrEquivalent</li>
     * <li>associates</li>
     * <li>international</li>
     * <li>bachelors</li>
     * <li>somePostgraduate</li>
     * <li>masters</li>
     * <li>intermediategraduate</li>
     * <li>professional</li>
     * <li>postprofessional</li>
     * <li>doctorate</li>
     * <li>postdoctorate</li>
     * </ul>
    */
    public List<String> DegreeTypes;
    
    /**
     * Results must have at least one of the specified employers. Supports (*, ?) wildcard 
     * characters after the third character in the term as defined in
     * https://developer.textkernel.com/Sovren/v10/ai-matching/overview/querying/#ai-filtering-special-operators
    */
    public List<String> Employers;
    
    /** When {@code true}, at least one employer in {@link #Employers} must be found in the current time frame.*/
    public boolean EmployersMustAllBeCurrentEmployer;
    
    /** When specified, total work experience must fall within this range.*/
    public IntegerRange MonthsExperience;
    
    /** Results must be written in one of the specified languages. (2-letter ISO 639-1 language codes)*/
    public List<String> DocumentLanguages;
    
    /** Results must have/require at least one of the specified skills.*/
    public List<SkillFilter> Skills;
    
    /**
     * When {@code true}, <b>all</b> of the skills in {@link #Skills} must be found. By default, this is {@code false}, 
     * which means that <b>only one</b> of the {@link #Skills} must be found. 
    */
    public boolean SkillsMustAllExist;
    
    /** Results must have an education with a normalized GPA of .75 or higher (for example, 3.0/4.0 or higher).*/
    public boolean IsTopStudent;
    
    /** Results must have an education section listed as '- current'.*/
    public boolean IsCurrentStudent;
    
    /** Results must have graduated within the last 18 months.*/
    public boolean IsRecentGraduate;
    
    /** Results must have at least one of the specified job titles.*/
    public List<JobTitleFilter> JobTitles;
    
    /**
     * Results must have at least one of the following types of executive experience:
     * <ul>
     * <li>None</li>
     * <li>Executive</li>
     * <li>Admin</li>
     * <li>Accounting</li>
     * <li>Operations</li>
     * <li>Financial</li>
     * <li>Marketing</li>
     * <li>Business_Dev</li>
     * <li>IT</li>
     * <li>General</li>
     * <li>Learning</li>
     * </ul>
    */
    public List<String> ExecutiveType;
    
    /**
     * Results must have at least one of the specified certifications. Supports (*, ?) wildcard 
     * characters after the third character in the term as defined in
     * https://developer.textkernel.com/Sovren/v10/ai-matching/overview/querying/#ai-filtering-special-operators
    */
    public List<String> Certifications;
    
    /** Results must have management experience within this months range.*/
    public IntegerRange MonthsManagementExperience;
    
    /**
     * Results must currently have at least one of the following management levels:
     * <ul>
     * <li>None</li>
     * <li>Low</li>
     * <li>Mid</li>
     * <li>High</li>
     * </ul>
    */
    public String CurrentManagementLevel;
    
    /**
     * Results must have/require these language competencies (2-letter ISO 639-1 language codes).
     * <p>Either all or at least one are required depending on the value of {@link #LanguagesKnownMustAllExist}
    */
    public List<String> LanguagesKnown;
    
    /**
     * When {@code true}, <b>all</b> of the languages in {@link #LanguagesKnown} must be found.
     * By default, this is {@code false}, which means that <b>only one</b> of the {@link #LanguagesKnown} must be found.
    */
    public boolean LanguagesKnownMustAllExist;
    
    /**
     * Results must contain at least one of the specified best-fit taxonomy IDs or best-fit subtaxonomy IDs.
     * <p>See {@link Taxonomy#getSovrenDefaults()}
    */
    public List<String> Taxonomies;

    /** 
     * Results much have {@link ExperienceSummary#AverageMonthsPerEmployer} within this range.
     * Only applicable for resumes; setting this when filtering jobs will cause an error.
    */
    public IntegerRange AverageMonthsPerEmployer;

    /** 
     * Results much have {@link ExperienceSummary#FulltimeDirectHirePredictiveIndex} within this range.
     * Only applicable for resumes; setting this when filtering jobs will cause an error.
    */
    public IntegerRange JobPredictiveIndex;
}
