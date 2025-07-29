// Copyright © 2023 Textkernel BV. All rights reserved.
// This file is provided for use by, or on behalf of, Textkernel licensees
// within the terms of their license of Textkernel products or Textkernel customers
// within the Terms of Service pertaining to the Textkernel SaaS products.

package com.textkernel.tx.services;

import java.util.List;

import com.textkernel.tx.DataCenter;
import com.textkernel.tx.models.api.dataenrichment.TaxonomyFormat;

public class ApiEndpoints {

    private DataCenter _dataCenter;

    ApiEndpoints(DataCenter dataCenter) {
        if (dataCenter == null) {
            throw new IllegalArgumentException("dataCenter cannot be null");
        }
        _dataCenter = dataCenter;
    }

    private static String sanitize(String indexOrDocId) throws IllegalArgumentException {
        if (indexOrDocId == null || indexOrDocId.length() == 0) {
            throw new IllegalArgumentException("Index or document id is null or empty");
        }

        for (char c : indexOrDocId.toCharArray()) {
            //if its not a letter, digit, dash, or underscore, invalid
            if (!(Character.isLetterOrDigit(c) || c == '-' || c == '_')) {
                String charName = Character.isWhitespace(c) ? "whitespace" : String.valueOf(c);
                throw new IllegalArgumentException("Index or document id contains an invalid character: " + charName);
            }
        }

        return indexOrDocId;
    }

    String account() { return _dataCenter.Url + "/account"; }
    String parseResume() { return _dataCenter.Url + "/parser/resume"; }
    String parseJob() { return _dataCenter.Url + "/parser/joborder"; }

    String index(String id) { return _dataCenter.Url + "/index/" + sanitize(id); }
    String indexDocumentCount(String id) { return _dataCenter.Url + "/index/" + sanitize(id) + "/count"; }
    String allIndexes() { return _dataCenter.Url + "/index"; }

    String resume(String indexId, String docId) { return _dataCenter.Url + "/index/" + sanitize(indexId) + "/resume/" + sanitize(docId); }
    String job(String indexId, String docId) { return _dataCenter.Url + "/index/" + sanitize(indexId) + "/joborder/" + sanitize(docId); }
    String multipleResumes(String indexId) { return _dataCenter.Url + "/index/" + sanitize(indexId) + "/resumes"; }
    String multipleJobs(String indexId) { return _dataCenter.Url + "/index/" + sanitize(indexId) + "/joborders"; }
    String document(String indexId, String docId) { return _dataCenter.Url + "/index/" + sanitize(indexId) + "/documents/" + sanitize(docId); }
    String multipleDocuments(String indexId) { return _dataCenter.Url + "/index/" + sanitize(indexId) + "/documents"; }

    String matchResume() { return _dataCenter.Url + "/matcher/resume"; }
    String matchJob() { return _dataCenter.Url + "/matcher/joborder"; }
    String matchDocId(String indexId, String docId) { return _dataCenter.Url + "/matcher/indexes/" + sanitize(indexId) + "/documents/" + sanitize(docId); }
    String search() { return _dataCenter.Url + "/searcher"; }

    String bimetricScoreResume() { return _dataCenter.Url + "/scorer/bimetric/resume"; }
    String bimetricScoreJob() { return _dataCenter.Url + "/scorer/bimetric/joborder"; }

    String geocodeResume() { return _dataCenter.Url + "/geocoder/resume"; }
    String geocodeJob() { return _dataCenter.Url + "/geocoder/joborder"; }
    String geocodeAndIndexResume() { return _dataCenter.Url + "/geocodeAndIndex/resume"; }
    String geocodeAndIndexJob() { return _dataCenter.Url + "/geocodeAndIndex/joborder"; }

    static String DESVersion(boolean includeCerts) { return includeCerts ? "/v2" : ""; }

    String desSkillsGetTaxonomy(TaxonomyFormat format, boolean includeCerts) { return _dataCenter.Url + "/skills" + DESVersion(includeCerts) + "/taxonomy?format="+ format; }
    String desSkillsGetMetadata(boolean includeCerts) { return _dataCenter.Url + "/skills" + DESVersion(includeCerts) + "/metadata"; }
    String desSkillsNormalize(boolean includeCerts) { return _dataCenter.Url + "/skills" + DESVersion(includeCerts) + "/normalize"; }
    String desSkillsExtract(boolean includeCerts) { return _dataCenter.Url + "/skills" + DESVersion(includeCerts) + "/extract"; }
    String desSkillsLookup(boolean includeCerts) { return _dataCenter.Url + "/skills" + DESVersion(includeCerts) + "/lookup"; }
    String desSkillsAutoComplete(boolean includeCerts) { return _dataCenter.Url + "/skills" + DESVersion(includeCerts) + "/autoComplete"; }
    String desProfessionsGetTaxonomy(TaxonomyFormat format, String language) { return _dataCenter.Url + "/professions/taxonomy?format="+ format +"&language="+ language; }
    String desProfessionsGetMetadata() { return _dataCenter.Url + "/professions/metadata"; }
    String desProfessionsNormalize() { return _dataCenter.Url + "/professions/normalize"; }
    String desProfessionsLookup() { return _dataCenter.Url + "/professions/lookup"; }
    String desProfessionsAutoComplete() { return _dataCenter.Url + "/professions/autoComplete"; }
    String desOntologySuggestSkillsFromProfessions(boolean includeCerts) { return _dataCenter.Url + "/ontology" + DESVersion(includeCerts) + "/suggest-skills-from-professions"; }
    String desOntologySuggestSkillsFromSkills(boolean includeCerts) { return _dataCenter.Url + "/ontology" + DESVersion(includeCerts) + "/suggest-skills-from-skills"; }
    String desOntologyCompareProfessions(boolean includeCerts) { return _dataCenter.Url + "/ontology" + DESVersion(includeCerts) + "/compare-professions"; }
    String desOntologySuggestProfessions(boolean includeCerts) { return _dataCenter.Url + "/ontology" + DESVersion(includeCerts) + "/suggest-professions"; }
    String desOntologyCompareSkillsToProfession(boolean includeCerts) { return _dataCenter.Url + "/ontology" + DESVersion(includeCerts) + "/compare-skills-to-profession"; }
    String desOntologySkillsSimilarityScore(boolean includeCerts) { return _dataCenter.Url + "/ontology" + DESVersion(includeCerts) + "/skills-similarity-score"; }
  
    String jobDescriptionGenerate() { return _dataCenter.Url + "/job-description/generate"; }
    String jobDescriptionSuggestSkills() { return _dataCenter.Url + "/job-description/suggest-skills"; }
  
    String formatResume() { return _dataCenter.Url + "/formatter/resume/template"; }

    String matchV2CandidatesAddDocument(String documentId) { return _dataCenter.Url + "/matchv2/candidates/" + documentId; }
    String matchV2CandidatesDeleteDocuments(List<String> documentIds, String env) { 
        return String.format("%smatchv2/candidates?ids=%s&SearchAndMatchEnvironment=%s", _dataCenter.Url, String.join(",", documentIds), env);
    }
    String matchV2CandidatesSearch() { return _dataCenter.Url + "/matchv2/candidates/search"; }
    String matchV2CandidatesMatchDocument() { return _dataCenter.Url + "/matchv2/candidates/match"; }
    String matchV2CandidatesAutocomplete() { return _dataCenter.Url + "/matchv2/candidates/autocomplete"; }
    String matchV2JobsAddDocument(String documentId) { return _dataCenter.Url + "/matchv2/vacancies/" + documentId; }
    String matchV2JobsDeleteDocuments(List<String> documentIds, String env) { 
        return String.format("%smatchv2/vacancies?ids=%s&SearchAndMatchEnvironment=%s", _dataCenter.Url, String.join(",", documentIds), env);
    }
    String matchV2JobsSearch() { return _dataCenter.Url + "/matchv2/vacancies/search"; }
    String matchV2JobsMatchDocument() { return _dataCenter.Url + "/matchv2/vacancies/match"; }
    String matchV2JobsAutocomplete() { return _dataCenter.Url + "/matchv2/vacancies/autocomplete"; }
}
