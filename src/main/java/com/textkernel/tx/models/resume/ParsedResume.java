// Copyright © 2023 Textkernel BV. All rights reserved.
// This file is provided for use by, or on behalf of, Textkernel licensees
// within the terms of their license of Textkernel products or Textkernel customers
// within the Terms of Service pertaining to the Textkernel SaaS products.

package com.textkernel.tx.models.resume;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import com.google.gson.JsonParseException;
import com.textkernel.tx.models.ParsedDocument;
import com.textkernel.tx.models.resume.contactinfo.ContactInformation;
import com.textkernel.tx.models.resume.education.EducationHistory;
import com.textkernel.tx.models.resume.employment.EmploymentHistory;
import com.textkernel.tx.models.resume.skills.ResumeTaxonomyRoot;
import com.textkernel.tx.models.resume.skills.ResumeV2Skills;
import com.textkernel.tx.models.resume.military.MilitaryDetails;
import com.textkernel.tx.models.resume.military.SecurityCredential;
import com.textkernel.tx.models.resume.metadata.ResumeMetadata;
import com.textkernel.tx.utilities.TxJsonSerializer;
import com.textkernel.tx.models.api.parsing.SkillsSettings;

/**
* All of the information extracted while parsing a resume
*/
public class ParsedResume extends ParsedDocument {

    /** The candidate's contact information found on the resume*/
    public ContactInformation ContactInformation;

    /** The professional summary from the resume*/
    public String ProfessionalSummary;

    /** The candidate's written objective*/
    public String Objective;

    /** The cover letter, if present. */
    public String CoverLetter;

    /** Personal information provided by the candidate on the resume*/
    public PersonalAttributes PersonalAttributes;

    /** The candidate's education history found on the resume*/
    public EducationHistory Education;

    /** The candidate's employment/work history found on the resume*/
    public EmploymentHistory EmploymentHistory;

    /** 
     * All the skills found in the resume when {@link SkillsSettings#TaxonomyVersion} is set to (or defaults to) {@code V1}.
     * @deprecated use {@link #Skills} instead for better results
    */
    @Deprecated
    public List<ResumeTaxonomyRoot> SkillsData;

    /** Skills output when {@link SkillsSettings#TaxonomyVersion} is set to (or defaults to) {@code V2}.*/
    public ResumeV2Skills Skills;

    /** Certifications found on a resume.*/
    public List<Certification> Certifications;

    /**
     * Licenses found on a resume. These are professional licenses, not driving licenses.
     * For driving licenses, see {@link PersonalAttributes#DrivingLicense}
    */
    public List<LicenseDetails> Licenses;

    /** Associations/organizations found on a resume*/
    public List<Association> Associations;

    /** Any language competencies (fluent in, can read, can write, etc) found in the resume.*/
    public List<LanguageCompetency> LanguageCompetencies;

    /** Any military experience listed on the resume*/
    public List<MilitaryDetails> MilitaryExperience;

    /** Any security credentials/clearances listed on the resume*/
    public List<SecurityCredential> SecurityCredentials;

    /** References listed on a resume.*/
    public List<CandidateReference> References;

    /** Any achievements listed on the resume*/
    public List<String> Achievements;

    /** Any training listed on the resume*/
    public TrainingHistory Training;

    /** A standalone 'skills' section, if listed on the resume*/
    public String QualificationsSummary;

    /** Any hobbies listed on the resume*/
    public String Hobbies;

    /** Any patents listed on the resume*/
    public String Patents;

    /** Any publications listed on the resume*/
    public String Publications;

    /** Any speaking engagements/appearances listed on the resume*/
    public String SpeakingEngagements;

    /** Metadata about the parsed resume*/
    public ResumeMetadata ResumeMetadata;

    /**
     * A list of <a href="https://developer.textkernel.com/tx-platform/v10/ai-matching/overview/user-defined-tags/">User-Defined Tags</a> 
     * that are assigned to this resume. These are used to filter search/match queries in the AI Matching Engine.
     * <p><b>NOTE: you may add/remove these prior to indexing. This is the only property you may modify prior to indexing.</b>
    */
    public List<String> UserDefinedTags;

    /** 
     * @deprecated You should never create one of these. Instead, these are output by the Resume Parser.
     * The API does not support manually-created resumes to be used in the AI Matching engine.
     */
    @Deprecated
    public ParsedResume() {
    }

    /**
     * Load a parsed resume from a json file using UTF-8 encoding. This is useful when you have stored parse results to disk for use later.
     * @param path The full path to the json file
     * @return The deserialized {@link ParsedResume}
     * @throws IOException When an error occurs reading the file
     * @throws JsonParseException If you try to parse an invalid ParsedResume JSON string
     */
    public static ParsedResume fromFile(String path) throws IOException, JsonParseException {
        String fileContents = new String(Files.readAllBytes(Paths.get(path)), Charset.forName("utf8"));
        return fromJson(fileContents);
    }

    /**
     * Create a parsed resume from json. This is useful when you have stored parse results to disk for use later.
     * @param utf8json The UTF-8 encoded json string
     * @return The deserialized {@link ParsedResume}
     * @throws JsonParseException If you try to parse an invalid ParsedResume JSON string
     */
    public static ParsedResume fromJson(String utf8json) throws JsonParseException {
        ParsedResume newResume = TxJsonSerializer.deserialize(utf8json, ParsedResume.class);

        if (newResume.ResumeMetadata == null) {
            //this should never happen, it was bad json
            throw new JsonParseException("The provided JSON is not a valid ParsedResume created by the Resume Parser");
        }

        return newResume;
    }
}
