// Copyright © 2023 Textkernel BV. All rights reserved.
// This file is provided for use by, or on behalf of, Textkernel licensees
// within the terms of their license of Textkernel products or Textkernel customers
// within the Terms of Service pertaining to the Textkernel SaaS products.

package com.textkernel.tx.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.textkernel.tx.EnvironmentSettings;
import com.textkernel.tx.TxClient;
import com.textkernel.tx.exceptions.TxException;
import com.textkernel.tx.http.HttpResponse;
import com.textkernel.tx.models.api.dataenrichment.AutocompleteRequest;
import com.textkernel.tx.models.api.dataenrichment.GetMetadataResponse;
import com.textkernel.tx.models.api.dataenrichment.TaxonomyFormat;
import com.textkernel.tx.models.api.dataenrichment.ontology.request.CompareProfessionsRequest;
import com.textkernel.tx.models.api.dataenrichment.ontology.request.CompareSkillsToProfessionRequest;
import com.textkernel.tx.models.api.dataenrichment.ontology.request.SkillsSimilarityScoreRequest;
import com.textkernel.tx.models.api.dataenrichment.ontology.request.SuggestProfessionsRequest;
import com.textkernel.tx.models.api.dataenrichment.ontology.request.SuggestSkillsFromProfessionsRequest;
import com.textkernel.tx.models.api.dataenrichment.ontology.request.SuggestSkillsFromSkillsRequest;
import com.textkernel.tx.models.api.dataenrichment.ontology.response.CompareProfessionsResponse;
import com.textkernel.tx.models.api.dataenrichment.ontology.response.CompareSkillsToProfessionResponse;
import com.textkernel.tx.models.api.dataenrichment.ontology.response.SkillScore;
import com.textkernel.tx.models.api.dataenrichment.ontology.response.SkillsSimilarityScoreResponse;
import com.textkernel.tx.models.api.dataenrichment.ontology.response.SuggestProfessionsResponse;
import com.textkernel.tx.models.api.dataenrichment.ontology.response.SuggestSkillsResponse;
import com.textkernel.tx.models.api.dataenrichment.professions.request.LookupProfessionCodesRequest;
import com.textkernel.tx.models.api.dataenrichment.professions.request.NormalizeProfessionsRequest;
import com.textkernel.tx.models.api.dataenrichment.professions.response.GetProfessionsTaxonomyResponse;
import com.textkernel.tx.models.api.dataenrichment.professions.response.GetProfessionsTaxonomyResponseValue;
import com.textkernel.tx.models.api.dataenrichment.professions.response.LookupProfessionCodesResponse;
import com.textkernel.tx.models.api.dataenrichment.professions.response.NormalizeProfessionsResponse;
import com.textkernel.tx.models.api.dataenrichment.professions.response.ProfessionsAutoCompleteResponse;
import com.textkernel.tx.models.api.dataenrichment.skills.request.ExtractSkillsRequest;
import com.textkernel.tx.models.api.dataenrichment.skills.request.LookupSkillsRequest;
import com.textkernel.tx.models.api.dataenrichment.skills.request.NormalizeSkillsRequest;
import com.textkernel.tx.models.api.dataenrichment.skills.request.SkillsAutoCompleteRequest;
import com.textkernel.tx.models.api.dataenrichment.skills.response.AutoCompleteSkillsResponse;
import com.textkernel.tx.models.api.dataenrichment.skills.response.ExtractSkillsResponse;
import com.textkernel.tx.models.api.dataenrichment.skills.response.GetSkillsTaxonomyResponse;
import com.textkernel.tx.models.api.dataenrichment.skills.response.GetSkillsTaxonomyResponseValue;
import com.textkernel.tx.models.api.dataenrichment.skills.response.LookupSkillCodesResponse;
import com.textkernel.tx.models.api.dataenrichment.skills.response.NormalizeSkillsResponse;
import com.textkernel.tx.models.api.jobdescription.GenerateJobRequest;
import com.textkernel.tx.models.api.jobdescription.GenerateJobResponse;
import com.textkernel.tx.models.api.jobdescription.SuggestSkillsFromJobTitleRequest;
import com.textkernel.tx.models.api.jobdescription.SuggestSkillsFromJobTitleResponse;
import com.textkernel.tx.models.job.ParsedJob;
import com.textkernel.tx.models.resume.ParsedResume;
import com.textkernel.tx.models.resume.employment.Position;
import com.textkernel.tx.models.resume.skills.ResumeNormalizedSkill;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/** See {@link TxClient#skillsIntelligence()} */
public class SkillsIntelligenceService extends ServiceBase {

    /**
     * Do not use this. See {@link TxClient#skillsIntelligence()}
     * @param httpClient The http client for API calls
     * @param settings environment settings
     */
    public SkillsIntelligenceService(OkHttpClient httpClient, EnvironmentSettings settings) {
        super(httpClient, settings);
    }

    /**
     * Get all skills in the taxonomy with associated IDs and descriptions in all supported languages.
     * @param format The format of the returned taxonomy. <br>NOTE: if you set this to {@link TaxonomyFormat#csv}, only the {@link GetSkillsTaxonomyResponseValue#CsvOutput} will be populated.
     * @return The API response body
     * @throws TxException Thrown when an API error occurs
     */
    public GetSkillsTaxonomyResponse getSkillsTaxonomy(TaxonomyFormat format) throws TxException {
        Request apiRequest = new Request.Builder()
                .url(_endpoints.desSkillsGetTaxonomy(format, _settings.SkillsIntelligenceIncludeCertifications))
                .build();

        HttpResponse<GetSkillsTaxonomyResponse> response = executeRequest(apiRequest, GetSkillsTaxonomyResponse.class, getBodyIfDebug(apiRequest));
        return response.getData();
    }

    /**
     * Get all skills in the taxonomy with associated IDs and descriptions in all supported languages.
     * @return The API response body
     * @throws TxException Thrown when an API error occurs
     */
    public GetSkillsTaxonomyResponse getSkillsTaxonomy() throws TxException {
        return getSkillsTaxonomy(TaxonomyFormat.json);
    }

    /**
     * Get metadata about the skills taxonomy/service.
     * @return The API response body
     * @throws TxException Thrown when an API error occurs
     */
    public GetMetadataResponse getSkillsTaxonomyMetadata() throws TxException {
        Request apiRequest = new Request.Builder()
                .url(_endpoints.desSkillsGetMetadata(_settings.SkillsIntelligenceIncludeCertifications))
                .build();

        HttpResponse<GetMetadataResponse> response = executeRequest(apiRequest, GetMetadataResponse.class, getBodyIfDebug(apiRequest));
        return response.getData();
    }

    /**
     * Returns normalized skills that begin with a given prefix, based on the chosen language(s). Each skill is associated with multiple descriptions. If any of the descriptions are a good completion of the given prefix, the skill is included in the results.
     * @param prefix The skill prefix to be completed. Must contain at least 1 character.
     * @param languages The language(s) used to search for matching skills (the language of the provided Prefix). A maximum of 5 languages can be provided. Must be one of the supported <a href="https://developer.textkernel.com/tx-platform/v10/data-enrichment/overview/#skills-languages">ISO codes</a>.<br>Default is 'en' only.
     * @param outputLanguage The language to ouput the found skill descriptions in (default is 'en'). Must be one of the supported <a href="https://developer.textkernel.com/tx-platform/v10/data-enrichment/overview/#skills-languages">ISO codes</a>.
     * @param types If specified, only these types of skills will be returned. The following values are acceptable: Professional, IT, Language, Soft, All.
     * @param limit The maximum number of returned skills. The default is 10 and the maximum is 100.
     * @return The API response body
     * @throws TxException Thrown when an API error occurs
     */
    public AutoCompleteSkillsResponse autocompleteSkill(String prefix, List<String> languages, String outputLanguage, List<String> types, int limit) throws TxException {
        SkillsAutoCompleteRequest request = new SkillsAutoCompleteRequest();
        request.Prefix = prefix;
        request.Limit = limit;
        request.Types = types;
        request.Languages = languages;
        request.OutputLanguage = outputLanguage;

        RequestBody body = createJsonBody(request);
        Request apiRequest = new Request.Builder()
            .url(_endpoints.desSkillsAutoComplete(_settings.SkillsIntelligenceIncludeCertifications))
            .post(body)
            .build();

        HttpResponse<AutoCompleteSkillsResponse> response = executeRequest(apiRequest, AutoCompleteSkillsResponse.class, getBodyIfDebug(apiRequest));
        return response.getData();
    }

    /**
     * Returns normalized skills that begin with a given prefix, based on the chosen language(s). Each skill is associated with multiple descriptions. If any of the descriptions are a good completion of the given prefix, the skill is included in the results.
     * @param prefix The skill prefix to be completed. Must contain at least 1 character.
     * @return The API response body
     * @throws TxException Thrown when an API error occurs
     */
    public AutoCompleteSkillsResponse autocompleteSkill(String prefix) throws TxException {
        return autocompleteSkill(prefix,null,null,null,10);
    }

    /**
     * Get the details associated with given skills in the taxonomy.
     * @param skillIds The IDs of the skills to get details about. A maximum of 100 IDs can be requested.
     * @param outputLanguage The language to use for the output skill descriptions. If not provided, defaults to en. If specified, must be one of the supported <a href="https://developer.textkernel.com/tx-platform/v10/data-enrichment/overview/#skills-languages">ISO codes</a>.<br>Default is 'en'.
     * @return The API response body
     * @throws TxException Thrown when an API error occurs
     */
    public LookupSkillCodesResponse lookupSkills(List<String> skillIds, String outputLanguage) throws TxException {
        LookupSkillsRequest request = new LookupSkillsRequest();
        request.SkillIds = skillIds;
        request.OutputLanguage = outputLanguage;

        RequestBody body = createJsonBody(request);
        Request apiRequest = new Request.Builder()
            .url(_endpoints.desSkillsLookup(_settings.SkillsIntelligenceIncludeCertifications))
            .post(body)
            .build();

        HttpResponse<LookupSkillCodesResponse> response = executeRequest(apiRequest, LookupSkillCodesResponse.class, getBodyIfDebug(apiRequest));
        return response.getData();
    }

    /**
     * Get the details associated with given skills in the taxonomy.
     * @param skillIds The IDs of the skills to get details about. A maximum of 100 IDs can be requested.
     * @return The API response body
     * @throws TxException Thrown when an API error occurs
     */
    public LookupSkillCodesResponse lookupSkills(List<String> skillIds) throws TxException {
        return lookupSkills(skillIds,null);
    }

    /**
     * Normalize the given skills to the most closely-related skills in the taxonomy.
     * @param skills The list of skills to normalize (up to 50 skills, each skill may not exceed 100 characters).
     * @param language The language of the given skills. Must be one of the supported <a href="https://developer.textkernel.com/tx-platform/v10/data-enrichment/overview/#skills-languages">ISO codes</a>.<br>Default is 'en'.
     * @param outputLanguage The language to use for the output skill descriptions. Must be one of the supported <a href="https://developer.textkernel.com/tx-platform/v10/data-enrichment/overview/#skills-languages">ISO codes</a>.<br>Defaults to whatever is used for the 'language' parameter.
     * @return The API response body
     * @throws TxException Thrown when an API error occurs
     */
    public NormalizeSkillsResponse normalizeSkills(List<String> skills, String language, String outputLanguage) throws TxException {
        NormalizeSkillsRequest request = new NormalizeSkillsRequest();
        request.Skills = skills;
        request.Language = language;
        request.OutputLanguage = outputLanguage;

        RequestBody body = createJsonBody(request);
        Request apiRequest = new Request.Builder()
            .url(_endpoints.desSkillsNormalize(_settings.SkillsIntelligenceIncludeCertifications))
            .post(body)
            .build();

        HttpResponse<NormalizeSkillsResponse> response = executeRequest(apiRequest, NormalizeSkillsResponse.class, getBodyIfDebug(apiRequest));
        return response.getData();
    }

    /**
     * Normalize the given skills to the most closely-related skills in the taxonomy.
     * @param skills The list of skills to normalize (up to 50 skills, each skill may not exceed 100 characters).
     * @return The API response body
     * @throws TxException Thrown when an API error occurs
     */
    public NormalizeSkillsResponse normalizeSkills(List<String> skills) throws TxException {
        return normalizeSkills(skills,null,null);
    }

    /**
     * Extracts known skills from the given text.
     * @param text The text to extract skills from. There is a 24,000 character limit.
     * @param language The language of the input text. Must be one of the supported <a href="https://developer.textkernel.com/tx-platform/v10/data-enrichment/overview/#skills-languages">ISO codes</a>.<br>Default is 'en'.
     * @param outputLanguage The language to use for the output skill descriptions. If not provided, defaults to the input language. Must be one of the supported <a href="https://developer.textkernel.com/tx-platform/v10/data-enrichment/overview/#skills-languages">ISO codes</a>.
     * @param threshold A value from [0 - 1] for the minimum confidence threshold for extracted skills. Lower values will return more skills, but also increase the likelihood of ambiguity-related errors. The recommended and default value is 0.5.
     * @return The API response body
     * @throws TxException Thrown when an API error occurs
     */
    public ExtractSkillsResponse extractSkills(String text, String language, String outputLanguage, float threshold) throws TxException {
        ExtractSkillsRequest request = new ExtractSkillsRequest();
        request.Text = text;
        request.Language = language;
        request.OutputLanguage = outputLanguage;
        request.Threshold = threshold;

        RequestBody body = createJsonBody(request);
        Request apiRequest = new Request.Builder()
            .url(_endpoints.desSkillsExtract(_settings.SkillsIntelligenceIncludeCertifications))
            .post(body)
            .build();

        HttpResponse<ExtractSkillsResponse> response = executeRequest(apiRequest, ExtractSkillsResponse.class, getBodyIfDebug(apiRequest));
        return response.getData();
    }

    /**
     * Extracts known skills from the given text.
     * @param text The text to extract skills from. There is a 24,000 character limit.
     * @return The API response body
     * @throws TxException Thrown when an API error occurs
     */
    public ExtractSkillsResponse extractSkills(String text) throws TxException {
        return extractSkills(text,null,null,0.5f);
    }

    /**
     * Get all professions in the taxonomy with associated IDs and descriptions in all supported languages.
     * @param language The language parameter returns the taxonomy with descriptions only in that specified language. If not specified, descriptions in all languages are returned. Must be specified as one of the supported <a href="https://developer.textkernel.com/tx-platform/v10/data-enrichment/overview/#professions-languages">ISO codes</a>.
     * @param format The format of the returned taxonomy. <br>NOTE: if you set this to {@link TaxonomyFormat#csv}, only the {@link GetProfessionsTaxonomyResponseValue#CsvOutput} will be populated.
     * @return The API response body
     * @throws TxException Thrown when an API error occurs
     */
    public GetProfessionsTaxonomyResponse getProfessionsTaxonomy(String language, TaxonomyFormat format) throws TxException {
        Request apiRequest = new Request.Builder()
                .url(_endpoints.desProfessionsGetTaxonomy(format,language))
                .build();

        HttpResponse<GetProfessionsTaxonomyResponse> response = executeRequest(apiRequest, GetProfessionsTaxonomyResponse.class, getBodyIfDebug(apiRequest));
        return response.getData();
    }

    /**
     * Get all professions in the taxonomy with associated IDs and descriptions in all supported languages.
     * @return The API response body
     * @throws TxException Thrown when an API error occurs
     */
    public GetProfessionsTaxonomyResponse getProfessionsTaxonomy() throws TxException {
        return getProfessionsTaxonomy(null,TaxonomyFormat.json);
    }

    /**
     * Get metadata about the professions taxonomy/service.
     * @return The API response body
     * @throws TxException Thrown when an API error occurs
     */
    public GetMetadataResponse getProfessionsTaxonomyMetadata() throws TxException {
        Request apiRequest = new Request.Builder()
                .url(_endpoints.desProfessionsGetMetadata())
                .build();

        HttpResponse<GetMetadataResponse> response = executeRequest(apiRequest, GetMetadataResponse.class, getBodyIfDebug(apiRequest));
        return response.getData();
    }

    /**
     * Returns normalized professions that begin with a given prefix, based on the chosen language(s). Each profession is associated with multiple descriptions. If any of the descriptions are a good completion of the given prefix, the profession is included in the results.
     * @param prefix The job title prefix to be completed. Must contain at least 1 character.
     * @param languages The language(s) used to search for matching professions (the language of the provided Prefix). A maximum of 5 languages can be provided. Must be one of the supported <a href="https://developer.textkernel.com/tx-platform/v10/data-enrichment/overview/#professions-languages">ISO codes</a>. Default is 'en' only.
     * @param outputLanguage The language to ouput the found professions in (default is 'en'). Must be one of the supported <a href="https://developer.textkernel.com/tx-platform/v10/data-enrichment/overview/#professions-languages">ISO codes</a>.
     * @param limit The maximum number of returned professions. The default is 10 and the maximum is 100.
     * @return The API response body
     * @throws TxException Thrown when an API error occurs
     */
    public ProfessionsAutoCompleteResponse autocompleteProfession(String prefix, List<String> languages, String outputLanguage, int limit) throws TxException {
        AutocompleteRequest request = new AutocompleteRequest();
        request.Prefix = prefix;
        request.Limit = limit;
        request.Languages = languages;
        request.OutputLanguage = outputLanguage;

        RequestBody body = createJsonBody(request);
        Request apiRequest = new Request.Builder()
            .url(_endpoints.desProfessionsAutoComplete())
            .post(body)
            .build();

        HttpResponse<ProfessionsAutoCompleteResponse> response = executeRequest(apiRequest, ProfessionsAutoCompleteResponse.class, getBodyIfDebug(apiRequest));
        return response.getData();
    }

    /**
     * Returns normalized professions that begin with a given prefix, based on the default language of english. Each profession is associated with multiple descriptions. If any of the descriptions are a good completion of the given prefix, the profession is included in the results.
     * @param prefix The job title prefix to be completed. Must contain at least 1 character.
     * @return The API response body
     * @throws TxException Thrown when an API error occurs
     */
    public ProfessionsAutoCompleteResponse autocompleteProfession(String prefix) throws TxException {
        List<String> languages = new ArrayList<>();
        languages.add("en");
        return  autocompleteProfession(prefix,languages,"en",10);
    }

    /**
     * Normalize the given job titles to the most closely-related professions in the taxonomy.
     * @param jobTitles The list of job titles to normalize (up to 10 job titles, each job title may not exceed 400 characters).
     * @param language The language of the input job titles. Must be one of the supported <a href="https://developer.textkernel.com/tx-platform/v10/data-enrichment/overview/#professions-languages">ISO codes</a>.<br>Default is 'en'.
     * @param outputLanguage The language to use for descriptions of the returned normalized professions. Must be one of the supported <a href="https://developer.textkernel.com/tx-platform/v10/data-enrichment/overview/#professions-languages">ISO codes</a>.<br>Defaults to whatever is used for the 'language' parameter.
     * @return The API response body
     * @throws TxException Thrown when an API error occurs
     */
    public NormalizeProfessionsResponse normalizeProfessions(List<String> jobTitles, String language, String outputLanguage) throws TxException {
        NormalizeProfessionsRequest request = new NormalizeProfessionsRequest();
        request.JobTitles = jobTitles;
        request.Language = language;
        request.OutputLanguage = outputLanguage;

        RequestBody body = createJsonBody(request);
        Request apiRequest = new Request.Builder()
            .url(_endpoints.desProfessionsNormalize())
            .post(body)
            .build();

        HttpResponse<NormalizeProfessionsResponse> response = executeRequest(apiRequest, NormalizeProfessionsResponse.class, getBodyIfDebug(apiRequest));
        return response.getData();
    }

    /**
     * Normalize the given job titles to the most closely-related professions in the taxonomy.
     * @param jobTitles The list of job titles to normalize (up to 10 job titles, each job title may not exceed 400 characters).
     * @return The API response body
     * @throws TxException Thrown when an API error occurs
     */
    public NormalizeProfessionsResponse normalizeProfessions(List<String> jobTitles) throws TxException {
        return normalizeProfessions(jobTitles,null,null);
    }

    /**
     * Get details for the given professions in the taxonomy.
     * @param codeIds The profession code IDs to get details about from the <a href="https://developer.textkernel.com/tx-platform/v10/data-enrichment/overview/#professions-taxonomies">Professions Taxonomy</a>.
     * @param outputLanguage The language to use for professions descriptions (default is en). Must be an allowed <a href="https://developer.textkernel.com/tx-platform/v10/data-enrichment/overview/#professions-languages">ISO code</a>. <br>Default is 'en'.
     * @return The API response body
     * @throws TxException Thrown when an API error occurs
     */
    public LookupProfessionCodesResponse lookupProfessions(List<Integer> codeIds, String outputLanguage) throws TxException {
        LookupProfessionCodesRequest request = new LookupProfessionCodesRequest();
        request.CodeIds = codeIds;
        request.OutputLanguage = outputLanguage;

        RequestBody body = createJsonBody(request);
        Request apiRequest = new Request.Builder()
            .url(_endpoints.desProfessionsLookup())
            .post(body)
            .build();

        HttpResponse<LookupProfessionCodesResponse> response = executeRequest(apiRequest, LookupProfessionCodesResponse.class, getBodyIfDebug(apiRequest));
        return response.getData();
    }

    /**
     * Get details for the given professions in the taxonomy.
     * @param codeIds The profession code IDs to get details about from the <a href="https://developer.textkernel.com/tx-platform/v10/data-enrichment/overview/#professions-taxonomies">Professions Taxonomy</a>.
     * @return The API response body
     * @throws TxException Thrown when an API error occurs
     */
    public LookupProfessionCodesResponse lookupProfessions(List<Integer> codeIds) throws TxException {
        return lookupProfessions(codeIds,null);
    }

    /**
     * Compare two professions based on the skills associated with each.
     * @param profession1 A profession code ID from the <a href="https://developer.textkernel.com/tx-platform/v10/data-enrichment/overview/#professions-taxonomies">Professions Taxonomy</a> to compare.
     * @param profession2 A profession code ID from the <a href="https://developer.textkernel.com/tx-platform/v10/data-enrichment/overview/#professions-taxonomies">Professions Taxonomy</a> to compare.
     * @param outputLanguage The language to use for the returned descriptions. If not provided, no descriptions are returned. Must be one of the supported <a href="https://developer.textkernel.com/tx-platform/v10/data-enrichment/overview/#skills-languages">ISO code</a>
     * @return The API response body
     * @throws TxException Thrown when an API error occurs
     */
    public CompareProfessionsResponse compareProfessions(int profession1, int profession2, String outputLanguage) throws TxException {
        CompareProfessionsRequest request = new CompareProfessionsRequest();
        request.ProfessionACodeId = profession1;
        request.ProfessionBCodeId = profession2;
        request.OutputLanguage = outputLanguage;

        RequestBody body = createJsonBody(request);
        Request apiRequest = new Request.Builder()
            .url(_endpoints.desOntologyCompareProfessions(_settings.SkillsIntelligenceIncludeCertifications))
            .post(body)
            .build();

        HttpResponse<CompareProfessionsResponse> response = executeRequest(apiRequest, CompareProfessionsResponse.class, getBodyIfDebug(apiRequest));
        return response.getData();
    }

    /**
     * Compare a given set of skills to the skills related to a given profession.
     * @param professionCodeId The profession code ID from the <a href="https://developer.textkernel.com/tx-platform/v10/data-enrichment/overview/#professions-taxonomies">Professions Taxonomy</a> to compare the skill set to.
     * @param outputLanguage The language to use for the returned descriptions. If not provided, no descriptions are returned. Must be one of the supported <a href="https://developer.textkernel.com/tx-platform/v10/data-enrichment/overview/#skills-languages">ISO code</a>
     * @param skills The skills which should be compared against the given profession. The list can contain up to 50 skills.
     * @return The API response body
     * @throws TxException Thrown when an API error occurs
     */
    public CompareSkillsToProfessionResponse compareSkillsToProfessions(int professionCodeId, String outputLanguage, List<SkillScore> skills) throws TxException {
        CompareSkillsToProfessionRequest request = new CompareSkillsToProfessionRequest();
        request.Skills = new ArrayList<SkillScore>();
        int amountOfSkills = skills.size() > 50 ? 50 : skills.size();

        for(int i = 0; i < amountOfSkills; i++) {
            request.Skills.add(skills.get(i));
        };
        request.ProfessionCodeId = professionCodeId;
        request.OutputLanguage = outputLanguage;

        RequestBody body = createJsonBody(request);
        Request apiRequest = new Request.Builder()
            .url(_endpoints.desOntologyCompareSkillsToProfession(_settings.SkillsIntelligenceIncludeCertifications))
            .post(body)
            .build();

        HttpResponse<CompareSkillsToProfessionResponse> response = executeRequest(apiRequest, CompareSkillsToProfessionResponse.class, getBodyIfDebug(apiRequest));
        return response.getData();
    }

    /**
     * Compare the skills of a candidate to the skills related to a job using the Ontology API.
     * @param resume The resume containing the skills of the candidate
     * @param professionCodeId The profession code ID from the <a href="https://developer.textkernel.com/tx-platform/v10/data-enrichment/overview/#professions-taxonomies">Professions Taxonomy</a> to compare the skill set to.
     * @param outputLanguage The language to use for the returned descriptions. If not provided, no descriptions are returned. Must be one of the supported <a href="https://developer.textkernel.com/tx-platform/v10/data-enrichment/overview/#skills-languages">ISO code</a>
     * @param weightSkillsByExperience Whether or not to give a higher weight to skills that the candidate has more experience with.
     * @return The API response body
     * @throws TxException Thrown when an API error occurs
     */
    public CompareSkillsToProfessionResponse compareSkillsToProfessions(
        ParsedResume resume,
        int professionCodeId,
        String outputLanguage,
        boolean weightSkillsByExperience) throws TxException {
        if(resume != null && resume.Skills != null && resume.Skills.Normalized != null && resume.Skills.Normalized.size() > 0){
            return compareSkillsToProfessions(professionCodeId, outputLanguage, getNormalizedSkillsFromResume(resume, weightSkillsByExperience));
        }
        throw new IllegalArgumentException("The resume must be parsed with V2 skills selected, and with skills normalization enabled");
    }

    /**
     * Suggests skills related to given professions. The service returns salient skills that are strongly associated with the professions.
     * @param professionCodeIds  The code IDs of the professions to suggest skills for.
     * @param limit The maximum amount of suggested skills returned. If not sure what value should be, provide 10 as default limit.
     * @param outputLanguage The language to use for the returned descriptions. If not provided, no descriptions are returned. Must be one of the supported <a href="https://developer.textkernel.com/tx-platform/v10/data-enrichment/overview/#skills-languages">ISO code</a>
     * @return The API response body
     * @throws TxException Thrown when an API error occurs
     */
    public SuggestSkillsResponse suggestSkillsFromProfessions(List<Integer> professionCodeIds, int limit, String outputLanguage) throws TxException {
        SuggestSkillsFromProfessionsRequest request = new SuggestSkillsFromProfessionsRequest();
        request.ProfessionCodeIds = professionCodeIds;
        request.Limit = limit;
        request.OutputLanguage = outputLanguage;

        RequestBody body = createJsonBody(request);
        Request apiRequest = new Request.Builder()
            .url(_endpoints.desOntologySuggestSkillsFromProfessions(_settings.SkillsIntelligenceIncludeCertifications))
            .post(body)
            .build();

        HttpResponse<SuggestSkillsResponse> response = executeRequest(apiRequest, SuggestSkillsResponse.class, getBodyIfDebug(apiRequest));
        return response.getData();
    }

    /**
     * Suggests skills related to given professions. The service returns salient skills that are strongly associated with the professions.
     * @param professionCodeIds  The code IDs of the professions to suggest skills for.
     * @param outputLanguage The language to use for the returned descriptions. If not provided, no descriptions are returned. Must be one of the supported <a href="https://developer.textkernel.com/tx-platform/v10/data-enrichment/overview/#skills-languages">ISO code</a>
     * @return The API response body
     * @throws TxException Thrown when an API error occurs
     */
    public SuggestSkillsResponse suggestSkillsFromProfessions(List<Integer> professionCodeIds, String outputLanguage) throws TxException {
        return suggestSkillsFromProfessions(professionCodeIds, 10, outputLanguage);
    }

    /**
     * Suggests skills related to a resume based on the recent professions in the resume.
     * @param resume The resume to suggest skills for (based on the professions in the resume).
     * @param outputLanguage The language to use for the returned descriptions. If not provided, no descriptions are returned. Must be one of the supported <a href="https://developer.textkernel.com/tx-platform/v10/data-enrichment/overview/#skills-languages">ISO code</a>
     * @return The API response body
     * @throws TxException Thrown when an API error occurs
     */
    public SuggestSkillsResponse suggestSkillsFromProfessions(ParsedResume resume, String outputLanguage) throws TxException {
        if(resume != null && resume.EmploymentHistory != null && resume.EmploymentHistory.Positions != null){
            List<Integer> normalizedProfs = new ArrayList<Integer>();
            for(Position position: resume.EmploymentHistory.Positions){
                if (position != null && position.NormalizedProfession != null && position.NormalizedProfession.Profession != null && position.NormalizedProfession.Profession.CodeId != null){
                    normalizedProfs.add(position.NormalizedProfession.Profession.CodeId);
                }
            }

            if (normalizedProfs.size() > 0){
                return suggestSkillsFromProfessions(normalizedProfs, outputLanguage);
            }
        }
        throw new IllegalArgumentException("No professions were found in the resume, or the resume was parsed without professions normalization enabled");
    }

    /**
     * Suggests skills related to a resume based on the recent professions in the resume.
     * @param resume The resume to suggest skills for (based on the professions in the resume).
     * @return The API response body
     * @throws TxException Thrown when an API error occurs
     */
    public SuggestSkillsResponse suggestSkillsFromProfessions(ParsedResume resume) throws TxException {
        return suggestSkillsFromProfessions(resume, null);
    }

    /**
     * Suggests skills related to a job based on the profession title in the job.
     * @param job The resume to suggest skills for (based on the professions in the resume).
     * @param outputLanguage The language to use for the returned descriptions. If not provided, no descriptions are returned. Must be one of the supported <a href="https://developer.textkernel.com/tx-platform/v10/data-enrichment/overview/#skills-languages">ISO code</a>
     * @return The API response body
     * @throws TxException Thrown when an API error occurs
     */
    public SuggestSkillsResponse suggestSkillsFromProfessions(ParsedJob job, String outputLanguage) throws TxException {
        if(job != null && job.JobTitles != null && job.JobTitles.NormalizedProfession != null && job.JobTitles.NormalizedProfession.Profession != null && job.JobTitles.NormalizedProfession.Profession.CodeId != null){
            List<Integer> ids = new ArrayList<Integer>();
            ids.add(job.JobTitles.NormalizedProfession.Profession.CodeId);

            return suggestSkillsFromProfessions(ids, outputLanguage);
        }
        throw new IllegalArgumentException("No professions were found in the job, or the job was parsed without professions normalization enabled");
    }

    /**
     * Suggests skills related to a job based on the profession title in the job.
     * @param job The resume to suggest skills for (based on the professions in the resume).
     * @return The API response body
     * @throws TxException Thrown when an API error occurs
     */
    public SuggestSkillsResponse suggestSkillsFromProfessions(ParsedJob job) throws TxException {
        return suggestSkillsFromProfessions(job, null);
    }

    private List<SkillScore> getNormalizedSkillsFromResume(ParsedResume resume, boolean weightSkillsByExperience) {
        if(resume != null && resume.Skills != null && resume.Skills.Normalized != null && resume.Skills.Normalized.size() > 0){
            List<SkillScore> skills = new ArrayList<SkillScore>();

            ResumeNormalizedSkill maxExperienceSkill = Collections.max(resume.Skills.Normalized, Comparator.comparing(s -> s.MonthsExperience != null ? s.MonthsExperience.Value : 0));
            Integer maxExperience = Optional.ofNullable(maxExperienceSkill).map(s -> s.MonthsExperience).map(e -> e.Value).orElse(0);

            for(int i = 0; i < resume.Skills.Normalized.size(); i++) {
                ResumeNormalizedSkill curSkill = resume.Skills.Normalized.get(i);
                SkillScore newSkill = new SkillScore(curSkill.Id);
                int curMonthsExperience = Optional.ofNullable(curSkill.MonthsExperience).map(e -> e.Value).orElse(0);
                newSkill.Score = (weightSkillsByExperience && maxExperience > 0) ? curMonthsExperience / (float)maxExperience : 1; 

                skills.add(newSkill);
            }

            return skills;
        }
        throw new IllegalArgumentException("The resume must be parsed with V2 skills selected, and with skills normalization enabled.");
    }

    /**
     * Suggest professions based on the <b>skills</b> within a given resume.
     * @param resume The professions are suggested based on the <b>skills</b> within this resume.
     * @param limit The maximum amount of professions returned. If not sure what value should be, provide 10 as default limit.
     * @param returnMissingSkills Flag to enable returning a list of missing skills per suggested profession.
     * @param outputLanguage The language to use for the returned descriptions. If not provided, no descriptions are returned. Must be one of the supported <a href="https://developer.textkernel.com/tx-platform/v10/data-enrichment/overview/#professions-languages">ISO code</a>
     * @param weightSkillsByExperience Whether or not to give a higher weight to skills that the candidate has more experience with.
     * @return The API response body
     * @throws TxException Thrown when an API error occurs
     */
    public SuggestProfessionsResponse suggestProfessionsFromSkills(
        ParsedResume resume,
        int limit,
        boolean returnMissingSkills,
        String outputLanguage,
        boolean weightSkillsByExperience) throws TxException {
        if(resume != null && resume.Skills != null && resume.Skills.Normalized != null && resume.Skills.Normalized.size() > 0){
            return suggestProfessionsFromSkills(getNormalizedSkillsFromResume(resume, weightSkillsByExperience), limit, returnMissingSkills, outputLanguage);
        }
        throw new IllegalArgumentException("The resume must be parsed with V2 skills selected, and with skills normalization enabled.");
    }

    /**
     * Suggest professions based on the <b>skills</b> within a given resume.
     * @param resume The professions are suggested based on the <b>skills</b> within this resume. Defaults limit returned to 10 and does not return missing skills. Use another overload to specify these parameters.
     * @param outputLanguage The language to use for the returned descriptions. If not provided, no descriptions are returned. Must be one of the supported <a href="https://developer.textkernel.com/tx-platform/v10/data-enrichment/overview/#professions-languages">ISO code</a>
     * @return The API response body
     * @throws TxException Thrown when an API error occurs
     */
    public SuggestProfessionsResponse suggestProfessionsFromSkills(ParsedResume resume, String outputLanguage) throws TxException {
        return suggestProfessionsFromSkills(resume, 10, false, outputLanguage, true);
    }

    /**
     * Suggest professions based on the <b>skills</b> within a given resume.
     * @param resume The professions are suggested based on the <b>skills</b> within this resume. Defaults limit returned to 10 and does not return missing skills. Use another overload to specify these parameters.
     * @return The API response body
     * @throws TxException Thrown when an API error occurs
     */
    public SuggestProfessionsResponse suggestProfessionsFromSkills(ParsedResume resume) throws TxException {
        return suggestProfessionsFromSkills(resume, null);
    }

    /**
     * Suggest professions based on the <b>skills</b> within a given job.
     * @param job The professions are suggested based on the <b>skills</b> within this job.
     * @param limit The maximum amount of professions returned. If not sure what value should be, provide 10 as default limit.
     * @param returnMissingSkills Flag to enable returning a list of missing skills per suggested profession.
     * @param outputLanguage The language to use for the returned descriptions. If not provided, no descriptions are returned. Must be one of the supported <a href="https://developer.textkernel.com/tx-platform/v10/data-enrichment/overview/#professions-languages">ISO code</a>
     * @return The API response body
     * @throws TxException Thrown when an API error occurs
     */
    public SuggestProfessionsResponse suggestProfessionsFromSkills(ParsedJob job, int limit, boolean returnMissingSkills, String outputLanguage) throws TxException {
        if(job != null && job.Skills != null && job.Skills.Normalized != null && job.Skills.Normalized.size() > 0){
            List<SkillScore> skills = new ArrayList<SkillScore>();
            int amountOfSkills = job.Skills.Normalized.size() > 50 ? 50 : job.Skills.Normalized.size();
            for(int i = 0; i < amountOfSkills; i++) {
                skills.add(new SkillScore(job.Skills.Normalized.get(i).Id));
            }

            return suggestProfessionsFromSkills(skills, limit, returnMissingSkills, outputLanguage);
        }
        throw new IllegalArgumentException("The job must be parsed with V2 skills selected, and with skills normalization enabled");
    }

    /**
     * Suggest professions based on the <b>skills</b> within a given job.
     * @param job The professions are suggested based on the <b>skills</b> within this job. Defaults limit returned to 10 and does not return missing skills. Use another overload to specify these parameters.
     * @param outputLanguage The language to use for the returned descriptions. If not provided, no descriptions are returned. Must be one of the supported <a href="https://developer.textkernel.com/tx-platform/v10/data-enrichment/overview/#professions-languages">ISO code</a>
     * @return The API response body
     * @throws TxException Thrown when an API error occurs
     */
    public SuggestProfessionsResponse suggestProfessionsFromSkills(ParsedJob job, String outputLanguage) throws TxException {
        return suggestProfessionsFromSkills(job, 10, false, outputLanguage);
    }

    /**
     * Suggest professions based on the <b>skills</b> within a given job.
     * @param job The professions are suggested based on the <b>skills</b> within this job. Defaults limit returned to 10 and does not return missing skills. Use another overload to specify these parameters.
     * @return The API response body
     * @throws TxException Thrown when an API error occurs
     */
    public SuggestProfessionsResponse suggestProfessionsFromSkills(ParsedJob job) throws TxException {
        return suggestProfessionsFromSkills(job, null);
    }

    /**
     * Suggest professions based on a given set of skills.
     * @param skills The skills used to return the most relevant professions. The list can contain up to 50 skills.
     * @param limit The maximum amount of professions returned. If not sure what value should be, provide 10 as default limit.
     * @param returnMissingSkills Flag to enable returning a list of missing skills per suggested profession.
     * @param outputLanguage The language to use for the returned descriptions. If not provided, no descriptions are returned. Must be one of the supported <a href="https://developer.textkernel.com/tx-platform/v10/data-enrichment/overview/#professions-languages">ISO code</a>
     * @return The API response body
     * @throws TxException Thrown when an API error occurs
     */
    public SuggestProfessionsResponse suggestProfessionsFromSkills(
        List<SkillScore> skills,
        int limit,
        boolean returnMissingSkills,
        String outputLanguage) throws TxException {
        SuggestProfessionsRequest request = new SuggestProfessionsRequest();
        request.Skills = skills;
        request.Limit = limit;
        request.ReturnMissingSkills = returnMissingSkills;
        request.OutputLanguage = outputLanguage;

        RequestBody body = createJsonBody(request);
        Request apiRequest = new Request.Builder()
            .url(_endpoints.desOntologySuggestProfessions(_settings.SkillsIntelligenceIncludeCertifications))
            .post(body)
            .build();

        HttpResponse<SuggestProfessionsResponse> response = executeRequest(apiRequest, SuggestProfessionsResponse.class, getBodyIfDebug(apiRequest));
        return response.getData();
    }

    /**
     * Suggest professions based on a given set of skill IDs.
     * @param skillIds The skill IDs used to return the most relevant professions. The list can contain up to 50 skill IDs. Defaults limit returned to 10 and does not return missing skills. Use another overload to specify these parameters.
     * @param outputLanguage The language to use for the returned descriptions. If not provided, no descriptions are returned. Must be one of the supported <a href="https://developer.textkernel.com/tx-platform/v10/data-enrichment/overview/#professions-languages">ISO code</a>
     * @return The API response body
     * @throws TxException Thrown when an API error occurs
     */
    public SuggestProfessionsResponse suggestProfessionsFromSkills(List<String> skillIds, String outputLanguage) throws TxException {
        List<SkillScore> skills = skillIds.stream()
            .map(s -> new SkillScore(s))
            .collect(Collectors.toList());
        return suggestProfessionsFromSkills(skills, 10, false, outputLanguage);
    }

    /**
     * Returns skills related to a given skill or set of skills. The service returns closely related skills in a sense that
     * knowing the provided skills either implies knowledge about the returned related skills, or should make it considerably
     * easier to acquire knowledge about them.
     * @param skills The skills (and optionally, scores) for which the service should return related skills. The list can contain up to 50 skills.
     * @param limit The maximum amount of suggested skills returned. The maximum is 25.
     * @param outputLanguage The language to use for the returned descriptions. If not provided, no descriptions are returned. Must be one of the supported <a href="https://developer.textkernel.com/tx-platform/v10/data-enrichment/overview/#skills-languages">ISO code</a>
     * @return The API response body
     * @throws TxException Thrown when an API error occurs
     */
    public SuggestSkillsResponse suggestSkillsFromSkills(
        List<SkillScore> skills,
        int limit,
        String outputLanguage) throws TxException {
        SuggestSkillsFromSkillsRequest request = new SuggestSkillsFromSkillsRequest();
        request.Skills = skills;
        request.Limit = limit;
        request.OutputLanguage = outputLanguage;

        RequestBody body = createJsonBody(request);
        Request apiRequest = new Request.Builder()
            .url(_endpoints.desOntologySuggestSkillsFromSkills(_settings.SkillsIntelligenceIncludeCertifications))
            .post(body)
            .build();

        HttpResponse<SuggestSkillsResponse> response = executeRequest(apiRequest, SuggestSkillsResponse.class, getBodyIfDebug(apiRequest));
        return response.getData();
    }

    /**
     * Returns skills related to a given skill or set of skills. The service returns closely related skills in a sense that
     * knowing the provided skills either implies knowledge about the returned related skills, or should make it considerably
     * easier to acquire knowledge about them.
     * @param skillIds The skill IDs for which the service should return related skills. The list can contain up to 50 skills.
     * @param outputLanguage The language to use for the returned descriptions. If not provided, no descriptions are returned. Must be one of the supported <a href="https://developer.textkernel.com/tx-platform/v10/data-enrichment/overview/#skills-languages">ISO code</a>
     * @return The API response body
     * @throws TxException Thrown when an API error occurs
     */
    public SuggestSkillsResponse suggestSkillsFromSkills(List<String> skillIds, String outputLanguage) throws TxException {
        return suggestSkillsFromSkills(skillIds.stream().map(s -> new SkillScore(s)).collect(Collectors.toList()), 25, outputLanguage);
    }

    /**
     * Suggests skills related to a job (but not in the job) based on the skills in the job. The service returns closely related skills in a sense that
     * knowing the provided skills either implies knowledge about the returned related skills, or should make it considerably
     * easier to acquire knowledge about them.
     * @param job The job to suggest skills for (based on the skills in the job).
     * @param limit The maximum amount of suggested skills returned. The maximum is 25.
     * @param outputLanguage The language to use for the returned descriptions. If not provided, no descriptions are returned. Must be one of the supported <a href="https://developer.textkernel.com/tx-platform/v10/data-enrichment/overview/#skills-languages">ISO code</a>
     * @return The API response body
     * @throws TxException Thrown when an API error occurs
     */
    public SuggestSkillsResponse suggestSkillsFromSkills(
        ParsedJob job,
        int limit,
        String outputLanguage) throws TxException {
        if(job != null && job.Skills != null && job.Skills.Normalized != null && job.Skills.Normalized.size() > 0){
            List<SkillScore> skills = new ArrayList<SkillScore>();
            int amountOfSkills = job.Skills.Normalized.size() > 50 ? 50 : job.Skills.Normalized.size();
            for(int i = 0; i < amountOfSkills; i++) {
                skills.add(new SkillScore(job.Skills.Normalized.get(i).Id));
            }

            return suggestSkillsFromSkills(skills, limit, outputLanguage);
        }
        throw new IllegalArgumentException("The job must be parsed with V2 skills selected, and with skills normalization enabled");
    }

    /**
     * Suggests skills related to a job (but not in the job) based on the skills in the job. The service returns closely related skills in a sense that
     * knowing the provided skills either implies knowledge about the returned related skills, or should make it considerably
     * easier to acquire knowledge about them.
     * @param job The job to suggest skills for (based on the skills in the job).
     * @param outputLanguage The language to use for the returned descriptions. If not provided, no descriptions are returned. Must be one of the supported <a href="https://developer.textkernel.com/tx-platform/v10/data-enrichment/overview/#skills-languages">ISO code</a>
     * @return The API response body
     * @throws TxException Thrown when an API error occurs
     */
    public SuggestSkillsResponse suggestSkillsFromSkills(ParsedJob job, String outputLanguage) throws TxException {
        return suggestSkillsFromSkills(job, 25, outputLanguage);
    }

    /**
     * Suggests skills related to a resume (but not in the resume) based on the skills in the resume. The service returns closely related skills in a sense that
     * knowing the provided skills either implies knowledge about the returned related skills, or should make it considerably
     * easier to acquire knowledge about them.
     * @param resume The resume to suggest skills for (based on the skills in the resume).
     * @param limit The maximum amount of suggested skills returned. The maximum is 25.
     * @param outputLanguage The language to use for the returned descriptions. If not provided, no descriptions are returned. Must be one of the supported <a href="https://developer.textkernel.com/tx-platform/v10/data-enrichment/overview/#skills-languages">ISO code</a>
     * @param weightSkillsByExperience Whether or not to give a higher weight to skills that the candidate has more experience with.
     * @return The API response body
     * @throws TxException Thrown when an API error occurs
     */
    public SuggestSkillsResponse suggestSkillsFromSkills(
        ParsedResume resume,
        int limit,
        String outputLanguage,
        boolean weightSkillsByExperience) throws TxException {
        return suggestSkillsFromSkills(getNormalizedSkillsFromResume(resume, weightSkillsByExperience), limit, outputLanguage);
    }

    /**
     * Suggests skills related to a resume (but not in the resume) based on the skills in the resume. The service returns closely related skills in a sense that
     * knowing the provided skills either implies knowledge about the returned related skills, or should make it considerably
     * easier to acquire knowledge about them.
     * @param resume The resume to suggest skills for (based on the skills in the resume).
     * @param outputLanguage The language to use for the returned descriptions. If not provided, no descriptions are returned. Must be one of the supported <a href="https://developer.textkernel.com/tx-platform/v10/data-enrichment/overview/#skills-languages">ISO code</a>
     * @return The API response body
     * @throws TxException Thrown when an API error occurs
     */
    public SuggestSkillsResponse suggestSkillsFromSkills(ParsedResume resume, String outputLanguage) throws TxException {
        return suggestSkillsFromSkills(resume, 25, outputLanguage, true);
    }

    /**
     * Determines how closely related one set of skills is to another. The service defines closely related skills
     * such that knowing a skill either implies knowledge about another skill, or should make it considerably
     * easier to acquire knowledge about that skill.
     * @param skillSetA A set of skills (and optionally, scores) to score against the other set of skills. The list can contain up to 50 skills.
     * @param skillSetB A set of skills (and optionally, scores) to score against the other set of skills. The list can contain up to 50 skills.
     * @return The API response body
     * @throws TxException Thrown when an API error occurs
     */
    public SkillsSimilarityScoreResponse skillsSimilarityScore(List<SkillScore> skillSetA, List<SkillScore> skillSetB) throws TxException {
        SkillsSimilarityScoreRequest request = new SkillsSimilarityScoreRequest();
        request.SkillsA = skillSetA;
        request.SkillsB = skillSetB;
        
        RequestBody body = createJsonBody(request);
        Request apiRequest = new Request.Builder()
            .url(_endpoints.desOntologySkillsSimilarityScore(_settings.SkillsIntelligenceIncludeCertifications))
            .post(body)
            .build();

        HttpResponse<SkillsSimilarityScoreResponse> response = executeRequest(apiRequest, SkillsSimilarityScoreResponse.class, getBodyIfDebug(apiRequest));
        return response.getData();
    }

    /**
     * Generates a job description based on specified parameters.
     * @param request The request body
     * @return The API response body
     * @throws TxException Thrown when an API error occurs
     */
    public GenerateJobResponse generateJobDescription(GenerateJobRequest request) throws TxException {
        RequestBody body = createJsonBody(request);
        Request apiRequest = new Request.Builder()
            .url(_endpoints.jobDescriptionGenerate())
            .post(body)
            .build();

        HttpResponse<GenerateJobResponse> response = executeRequest(apiRequest, GenerateJobResponse.class, getBodyIfDebug(apiRequest));
        return response.getData();
    }

    /**
     * Takes a job title and suggests relevant skills.
     * @param jobTitle The title of the job for which skills are being suggested.
     * @param language Language of the suggested skills in ISO 639-1 code format.
     * @param limit Maximum number of skills to suggest. If not specified this parameter defaults to 10. This value cannot exceed 50.
     * @return The API response body
     * @throws TxException Thrown when an API error occurs
     */
    public SuggestSkillsFromJobTitleResponse suggestSkillsFromJobTitle(String jobTitle, String language, Integer limit) throws TxException {
        SuggestSkillsFromJobTitleRequest request = new SuggestSkillsFromJobTitleRequest();
        request.JobTitle = jobTitle;
        request.Language = language;
        request.Limit = limit;

        RequestBody body = createJsonBody(request);
        Request apiRequest = new Request.Builder()
            .url(_endpoints.jobDescriptionSuggestSkills())
            .post(body)
            .build();

        HttpResponse<SuggestSkillsFromJobTitleResponse> response = executeRequest(apiRequest, SuggestSkillsFromJobTitleResponse.class, getBodyIfDebug(apiRequest));
        return response.getData();
    }

    /**
     * Takes a job title and suggests relevant skills.
     * @param jobTitle The title of the job for which skills are being suggested.
     * @return The API response body
     * @throws TxException Thrown when an API error occurs
     */
    public SuggestSkillsFromJobTitleResponse suggestSkillsFromJobTitle(String jobTitle) throws TxException {
        return suggestSkillsFromJobTitle(jobTitle, "en", null);
    }
}
