// Copyright © 2023 Textkernel BV. All rights reserved.
// This file is provided for use by, or on behalf of, Textkernel licensees
// within the terms of their license of Textkernel products or Textkernel customers
// within the Terms of Service pertaining to the Textkernel SaaS products.

package com.textkernel.tx;

import com.textkernel.tx.models.api.dataenrichment.TaxonomyFormat;

class ApiEndpoints {

    private static String _matchUIPrefix = "/ui";
    private DataCenter _dataCenter;

    ApiEndpoints(DataCenter dataCenter) {
        _dataCenter = dataCenter;
    }

    private String prefix() {
        return prefix(false);
    }

    private String prefix(boolean isMatchUI) {
        if (isMatchUI && !_dataCenter.IsSaaS) {
            //throw new IllegalAccessException("Cannot call Matching UI on a self-hosted installation.");
            //do not throw this for now, it will just be a 404
        }

        String versionSuffix = "";
        if (_dataCenter.Version != null && !_dataCenter.Version.trim().isEmpty()){
            versionSuffix = "/" + _dataCenter.Version;
        }

        return _dataCenter.Root + (isMatchUI ? _matchUIPrefix : "") + versionSuffix;
    }

    private String sanitize(String indexOrDocId) throws IllegalArgumentException {
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

    String account() { return prefix() + "/account"; }
    String parseResume() { return prefix() + "/parser/resume"; }
    String parseJob() { return prefix() + "/parser/joborder"; }
    
    String index(String id) { return prefix() + "/index/" + sanitize(id); }
    String indexDocumentCount(String id) { return prefix() + "/index/" + sanitize(id) + "/count"; }
    String allIndexes() { return prefix() + "/index"; }
    
    String resume(String indexId, String docId) { return prefix() + "/index/" + sanitize(indexId) + "/resume/" + sanitize(docId); }
    String job(String indexId, String docId) { return prefix() + "/index/" + sanitize(indexId) + "/joborder/" + sanitize(docId); }
    String multipleResumes(String indexId) { return prefix() + "/index/" + sanitize(indexId) + "/resumes"; }
    String multipleJobs(String indexId) { return prefix() + "/index/" + sanitize(indexId) + "/joborders"; }
    String document(String indexId, String docId) { return prefix() + "/index/" + sanitize(indexId) + "/documents/" + sanitize(docId); }
    String multipleDocuments(String indexId) { return prefix() + "/index/" + sanitize(indexId) + "/documents"; }
    
    String matchResume(boolean isMatchUI) { return prefix(isMatchUI) + "/matcher/resume"; }
    String matchJob(boolean isMatchUI) { return prefix(isMatchUI) + "/matcher/joborder"; }
    String matchDocId(String indexId, String docId, boolean isMatchUI) { return prefix(isMatchUI) + "/matcher/indexes/" + sanitize(indexId) + "/documents/" + sanitize(docId); }
    String search(boolean isMatchUI) { return prefix(isMatchUI) + "/searcher"; }
    
    String bimetricScoreResume(boolean isMatchUI) { return prefix(isMatchUI) + "/scorer/bimetric/resume"; }
    String bimetricScoreJob(boolean isMatchUI) { return prefix(isMatchUI) + "/scorer/bimetric/joborder"; }
    
    String geocodeResume() { return prefix() + "/geocoder/resume"; }
    String geocodeJob() { return prefix() + "/geocoder/joborder"; }
    String geocodeAndIndexResume() { return prefix() + "/geocodeAndIndex/resume"; }
    String geocodeAndIndexJob() { return prefix() + "/geocodeAndIndex/joborder"; }

    String desSkillsGetTaxonomy(TaxonomyFormat format) { return prefix(false) + "/skills/taxonomy?format="+ format; }
    String desSkillsGetMetadata() { return prefix(false) + "/skills/metadata"; }
    String desSkillsNormalize() { return prefix(false) + "/skills/normalize"; }
    String desSkillsExtract() { return prefix(false) + "/skills/extract"; }
    String desSkillsLookup() { return prefix(false) + "/skills/lookup"; }
    String desSkillsAutoComplete() { return prefix(false) + "/skills/autoComplete"; }
    String desProfessionsGetTaxonomy(TaxonomyFormat format, String language) { return prefix(false) + "/professions/taxonomy?format="+ format +"&language="+ language; }
    String desProfessionsGetMetadata() { return prefix(false) + "/professions/metadata"; }
    String desProfessionsNormalize() { return prefix(false) + "/professions/normalize"; }
    String desProfessionsLookup() { return prefix(false) + "/professions/lookup"; }
    String desProfessionsAutoComplete() { return prefix(false) + "/professions/autoComplete"; }
    String desOntologySuggestSkillsFromProfessions() { return prefix(false) + "/ontology/suggest-skills-from-professions"; }
    String desOntologySuggestSkillsFromSkills() { return prefix(false) + "/ontology/suggest-skills-from-skills"; }
    String desOntologyCompareProfessions() { return prefix(false) + "/ontology/compare-professions"; }
    String desOntologySuggestProfessions() { return prefix(false) + "/ontology/suggest-professions"; }
    String desOntologyCompareSkillsToProfession() { return prefix(false) + "/ontology/compare-skills-to-profession"; }
    String desOntologySkillsSimilarityScore() { return prefix(false) + "/ontology/skills-similarity-score"; }
}
