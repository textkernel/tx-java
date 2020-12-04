// Copyright © 2020 Sovren Group, Inc. All rights reserved.
// This file is provided for use by, or on behalf of, Sovren licensees
// within the terms of their license of Sovren products or Sovren customers
// within the Terms of Service pertaining to the Sovren SaaS products.

package com.sovren.models.resume.employment;

/**
* A summary of a candidate's work history
*/
public class ExperienceSummary {
    
    /**
     * A paragraph of text that summarizes the candidate's experience. This paragraph is generated based on other data
     * points in the {@link ExperienceSummary}. It will be the same language as the resume for Czech, Dutch,
     * English, French, German, Greek, Hungarian, Italian, Norwegian, Russian, Spanish, and Swedish. To always generate the 
     * summary in English, set "OutputFormat.AllSummariesInEnglish = True;" in the configuration string when parsing.
     * <p><b>In order for this value to be accurate, you must have provided an accurate RevisionDate when you parsed this resume.</b>
    */
    public String Description;
    
    /**
     * The number of months of work experience as indicated by the range of 
     * start and end date values in the various jobs/positions in the resume. 
     * Overlapping date ranges are not double-counted. This value is NOT derived from text like "I have 15 years of experience".
     * <p><b>In order for this value to be accurate, you must have provided an accurate RevisionDate when you parsed this resume.</b>
    */
    public int MonthsOfWorkExperience;
    
    /**
     * The number of months of management experience as indicated by the range of 
     * start and end date values in the various jobs/positions in the resume that have been 
     * determined to be management-level positions. Overlapping date ranges are not double-counted.
     * This value is NOT derived from text like "I have 10 years of management experience".
     * <p><b>In order for this value to be accurate, you must have provided an accurate RevisionDate when you parsed this resume.</b>
    */
    public int MonthsOfManagementExperience;
    
    /**
     * If {@link #ManagementScore} is at least 30 (mid-level+), then the job titles are examined to
     * determine the best category for the executive experience. One of:
     * <ul>
     * <li>NONE</li>
     * <li>ADMIN</li>
     * <li>ACCOUNTING</li>
     * <li>BUSINESS_DEV</li>
     * <li>EXECUTIVE</li>
     * <li>FINANCIAL</li>
     * <li>GENERAL</li>
     * <li>IT</li>
     * <li>LEARNING</li>
     * <li>MARKETING</li>
     * <li>OPERATIONS</li>
     * </ul>
    */
    public String ExecutiveType;
    
    /**
     * The average number of months a candidate has spent at each employer. Note that this number is per employer, not per job.
     * <p><b>In order for this value to be accurate, you must have provided an accurate RevisionDate when you parsed this resume.</b>
    */
    public int AverageMonthsPerEmployer;
    
    /**
     * A score (0-100), where 0 means a candidate is more likely to have had (and want/pursue) short-term/part-time/temp/contracting 
     * jobs and 100 means a candidate is more likely to have had (and want/pursue) traditional full-time, direct-hire jobs.
     * <p><b>In order for this value to be accurate, you must have provided an accurate RevisionDate when you parsed this resume.</b>
    */
    public int FulltimeDirectHirePredictiveIndex;
    
    /** A paragraph of text that summarizes the candidate's management experience (in English).*/
    public String ManagementStory;
    
    /**
     * Computed level of management for the current position. One of:
     * <ul>
     * <li>low-or-no-level</li>
     * <li>low-level</li>
     * <li>mid-level</li>
     * <li>somewhat high-level</li>
     * <li>high-level</li>
     * <li>executive-level</li>
     * </ul>
    */
    public String CurrentManagementLevel;
    
    /**
     * The highest score calculated from any of the position titles. The score is based on the 
     * wording of the title, not on the experience described within the position description. 
     * <ul>
     * <li>0-29 = Low level</li>
     * <li>30-59 = Mid level</li>
     * <li>60+ = High level</li>
     * </ul>
    */
    public int ManagementScore;

    /**
     * Any abnormal findings about the candidate's career will be reported here. For example, if the candidate
     * held a management-level position in a previous job, but not their current job.
    */
    public String AttentionNeeded;
}
