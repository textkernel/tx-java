// Copyright © 2020 Sovren Group, Inc. All rights reserved.
// This file is provided for use by, or on behalf of, Sovren licensees
// within the terms of their license of Sovren products or Sovren customers
// within the Terms of Service pertaining to the Sovren SaaS products.

package com.sovren.models.job;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;

import com.google.gson.JsonParseException;
import com.sovren.models.Location;
import com.sovren.models.ParsedDocument;
import com.sovren.models.SovrenPrimitive;
import com.sovren.models.job.skills.JobTaxonomyRoot;
import com.sovren.models.job.skills.JobV2Skills;
import com.sovren.utilities.SovrenJsonSerializer;
import com.sovren.models.api.parsing.SkillsSettings;

/**
* All of the information extracted while parsing a job
*/
public class ParsedJob extends ParsedDocument {
    
    /** Whether or not the job is a management position. Used by Sovren for AI Matching*/
    public boolean CurrentJobIsManagement;
    
    /** The management score. Used by Sovren for AI Matching*/
    public SovrenPrimitive<Integer> HighestManagementScore;
    
    /** The management level. Used by Sovren for AI Matching*/
    public String ManagementLevel;
    
    /** What kind of executive position the job is, if any. Used by Sovren for AI Matching*/
    public String ExecutiveType;
    
    /** The minimum years experience for the job, if listed. Used by Sovren for AI Matching*/
    public SovrenPrimitive<Integer> MinimumYears;
    
    /** The maximum years experience for the job, if listed. Used by Sovren for AI Matching*/
    public SovrenPrimitive<Integer> MaximumYears;
    
    /** The minimum years of management experience, if listed. Used by Sovren for AI Matching*/
    public SovrenPrimitive<Integer> MinimumYearsManagement;
    
    /** The maximum years of management experience, if listed. Used by Sovren for AI Matching*/
    public SovrenPrimitive<Integer> MaximumYearsManagement;
    
    /** The required educational degree, if listed. Used by Sovren for AI Matching*/
    public String RequiredDegree;
    
    /** The start date of the job.*/
    public SovrenPrimitive<LocalDate> StartDate;
    
    /** The end date for the job, if listed.*/
    public SovrenPrimitive<LocalDate> EndDate;
    
    /** Section containing information about the job. Job description strictly includes duties, tasks, and responsibilities for the role with as little irrelevant text as possible.*/
    public String JobDescription;
    
    /** Full text of any requirements listed by the job.*/
    public String JobRequirements;

    /** Full text of any benefits listed by the job.*/
    public String Benefits;

    /** Full text of any employer description listed by the job.*/
    public String EmployerDescription;
    
    /** The job titles found in the job. Used by Sovren for AI Matching*/
    public JobTitles JobTitles;
    
    /** The employer names found in the job.*/
    public EmployerNames EmployerNames;
    
    /** The educational degrees found listed in the job. Used by Sovren for AI Matching*/
    public List<JobDegree> Degrees;
    
    /** Any school names listed in the job*/
    public List<String> SchoolNames;
    
    /** Any certifications/licenses listed in the job. Used by Sovren for AI Matching*/
    public List<String> CertificationsAndLicenses;
    
    /** Any languages listed in the job. Used by Sovren for AI Matching*/
    public List<String> LanguageCodes;
    
    /** The location of the job, if listed. If no job location is found, this is the location of the company, if listed.*/
    public Location CurrentLocation;

    /** Information about the application process.*/
    public ApplicationDetails ApplicationDetails;

    /**
     * The salary found for the position
     * If no lexical cues are available from the vacancy, the time scale is guessed based on predefined salary ranges. Here are some rough salary ranges (note: country-specific conditions may apply):
     * <ul>
     *  <li>1 or 2 digits salary (9, 12): hourly</li>
     *  <li>3 or 4 digits salary (3800, 5000): monthly</li>
     *  <li>5 digit salary (38000, 50000): yearly</li>
     * </ul>
     * If a monthly salary is extracted, to get the annual salary it is multiplied by 14 (if country = AT) or 12 (all other countries).
     */
    public PayRange Salary;

    /** The minimum number of working hours per week*/
    public SovrenPrimitive<Integer> MinimumWorkingHours;

    /** The maximum number of working hours per week*/
    public SovrenPrimitive<Integer> MaximumWorkingHours;

    /**
    * The type of working hours. One of:
    * <ul>
    * <li>regular</li>
    * <li>irregular</li>
    * </ul>
    */
    public String WorkingHours;

    /** Whether or not the position is remote. Includes fulltime, partial and temporary remote working opportunities*/
    public boolean IsRemote;

    /** Any drivers license requirements*/
    public List<String> DriversLicenses;

    /**
    * The type of employment. One of:
    * <ul>
    * <li>unspecified</li>
    * <li>fulltime</li>
    * <li>parttime</li>
    * <li>fulltime/parttime</li>
    * </ul>
    */
    public String EmploymentType;

    /**
    * The contract type. One of:
    * <ul>
    * <li>unspecified</li>
    * <li>permanent</li>
    * <li>temporary</li>
    * <li>possibly_permanent</li>
    * <li>interim</li>
    * <li>franchise</li>
    * <li>side</li>
    * <li>internship</li>
    * <li>voluntary</li>
    * <li>freelance</li>
    * <li>apprenticeship</li>
    * <li>assisted</li>
    * </ul>
    */
    public String ContractType;
    
    /** Terms of interest listed in the job*/
    public List<String> TermsOfInterest;
    
    /** Any owners of the job posting, if listed.*/
    public List<String> Owners;
    
    /** 
     * All the skills found in the job when {@link SkillsSettings#TaxonomyVersion} is set to (or defaults to) {@code V1}.
     * @deprecated use {@link #Skills} instead for better results
    */
    @Deprecated
    public List<JobTaxonomyRoot> SkillsData;

    /** Skills output when {@link SkillsSettings#TaxonomyVersion} is set to (or defaults to) {@code V2}.*/
    public JobV2Skills Skills;
    
    /** Metadata about the parsed job*/
    public JobMetadata JobMetadata;
    
    /**
     * A list of <a href="https://sovren.com/technical-specs/latest/rest-api/ai-matching/overview/user-defined-tags/">User-Defined Tags</a> 
     * that are assigned to this job. These are used to filter search/match queries in the AI Matching Engine.
     * <p><b>NOTE: you may add/remove these prior to indexing. This is the only property you may modify prior to indexing.</b>
    */
    public List<String> UserDefinedTags;

    /** 
     * @deprecated You should never create one of these. Instead, these are output by the Sovren Job Parser.
     * Sovren does not support manually-created jobs to be used in the AI Matching engine.
     */
    @Deprecated
    public ParsedJob() {
    }


    /**
     * Load a parsed job from a json file using UTF-8 encoding. This is useful when you have stored parse results to disk for use later.
     * @param path The full path to the json file
     * @return The deserialized {@link ParsedJob}
     * @throws IOException When an error occurs reading the file
     * @throws JsonParseException If you try to parse an invalid ParsedResume JSON string
     */
    public static ParsedJob fromFile(String path) throws IOException, JsonParseException {
        String fileContents = new String(Files.readAllBytes(Paths.get(path)), Charset.forName("utf8"));
        return fromJson(fileContents);
    }

    /**
     * Create a parsed job from json. This is useful when you have stored parse results to disk for use later.
     * @param utf8json The UTF-8 encoded json string
     * @return The deserialized {@link ParsedJob}
     * @throws JsonParseException If you try to parse an invalid ParsedResume JSON string
     */
    public static ParsedJob fromJson(String utf8json) throws JsonParseException {
        ParsedJob newJob = SovrenJsonSerializer.deserialize(utf8json, ParsedJob.class);

        if (newJob.JobMetadata == null) {
            //this should never happen, it was bad json
            throw new JsonParseException("The provided JSON is not a valid ParsedJob created by the Sovren Job Parser");
        }

        return newJob;
    }
}
