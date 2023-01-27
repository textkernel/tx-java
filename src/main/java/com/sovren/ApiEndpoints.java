// Copyright © 2020 Sovren Group, Inc. All rights reserved.
// This file is provided for use by, or on behalf of, Sovren licensees
// within the terms of their license of Sovren products or Sovren customers
// within the Terms of Service pertaining to the Sovren SaaS products.

package com.sovren;

class ApiEndpoints {

    private static String _matchUIPrefix = "/ui";
    private DataCenter _dataCenter;

    ApiEndpoints(DataCenter dataCenter) {
        _dataCenter = dataCenter;
    }

    private String versionSuffix() {
        String versionSuffix = "";
        if (_dataCenter.Version != null && !_dataCenter.Version.trim().isEmpty()){
            versionSuffix = "/" + _dataCenter.Version;
        }
        return versionSuffix;
    }
    
    private String prefix() {
        return _dataCenter.Root + versionSuffix();
    }

    private String prefix(boolean isMatchUI, boolean isDES) {
        if (isMatchUI && !_dataCenter.IsSovrenSaaS) {
            //throw new IllegalAccessException("Cannot call Matching UI on a self-hosted installation.");
            //do not throw this for now, it will just be a 404
        } else if (isDES && !_dataCenter.IsSovrenSaaS){
            //throw new IllegalAccessException("Cannot call Data Enrichment Services on a self-hosted installation.");
            //do not throw this for now, it will just be a 404
        }

        return _dataCenter.Root + "/" + (isMatchUI ? _matchUIPrefix : "") + versionSuffix();
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
    
    String matchResume(boolean isMatchUI) { return prefix(isMatchUI, false) + "/matcher/resume"; }
    String matchJob(boolean isMatchUI) { return prefix(isMatchUI, false) + "/matcher/joborder"; }
    String matchDocId(String indexId, String docId, boolean isMatchUI) { return prefix(isMatchUI, false) + "/matcher/indexes/" + sanitize(indexId) + "/documents/" + sanitize(docId); }
    String search(boolean isMatchUI) { return prefix(isMatchUI, false) + "/searcher"; }
    
    String bimetricScoreResume(boolean isMatchUI) { return prefix(isMatchUI, false) + "/scorer/bimetric/resume"; }
    String bimetricScoreJob(boolean isMatchUI) { return prefix(isMatchUI, false) + "/scorer/bimetric/joborder"; }
    
    String geocodeResume() { return prefix() + "/geocoder/resume"; }
    String geocodeJob() { return prefix() + "/geocoder/joborder"; }
    String geocodeAndIndexResume() { return prefix() + "/geocodeAndIndex/resume"; }
    String geocodeAndIndexJob() { return prefix() + "/geocodeAndIndex/joborder"; }

    String desSkillsGetTaxonomy(String format) { return prefix(false, true) + "/skills/taxonomy?format="+ format; }
    String desSkillsGetMetadata() { return prefix(false, true) + "/skills/metadata"; }
    String desSkillsNormalize() { return prefix(false, true) + "/skills/normalize"; }
    String desSkillsExtract() { return prefix(false, true) + "/skills/extract"; }
    String desSkillsLookup() { return prefix(false, true) + "/skills/lookup"; }
    String desSkillsAutoComplete() { return prefix(false, true) + "/skills/autoComplete"; }
    String desProfessionsGetTaxonomy(String format, String language) { return prefix(false, true) + "/professions/taxonomy?format="+ format +"&language="+ language; }
    String desProfessionsGetMetadata() { return prefix(false, true) + "/professions/metadata"; }
    String desProfessionsNormalize() { return prefix(false, true) + "/professions/normalize"; }
    String desProfessionsLookup() { return prefix(false, true) + "/professions/lookup"; }
    String desProfessionsAutoComplete() { return prefix(false, true) + "/professions/autoComplete"; }
    String desOntologySuggestSkills() { return prefix(false, true) + "/ontology/suggestSkills"; }
    String desOntologyCompareProfessions() { return prefix(false, true) + "/ontology/compareProfessions"; }
    String desOntologySuggestProfessions() { return prefix(false, true) + "/ontology/suggestProfessions"; }
    String desOntologyCompareSkillsToProfession() { return prefix(false, true) + "/ontology/compareSkillsToProfession"; }
}
