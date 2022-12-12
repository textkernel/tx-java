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

    private String prefix(boolean isMatchUI) {
        if (isMatchUI && !_dataCenter.IsSovrenSaaS) {
            //throw new IllegalAccessException("Cannot call Matching UI on a self-hosted installation.");
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

    String desSkillsGetTaxonomy(String format, String language) { return prefix() + "/skills/taxonomy?format="+ format +"&language="+ language; }
    String desSkillsNormalize() { return prefix() + "/skills/normalize"; }
    String desSkillsExtract() { return prefix() + "/skills/extract"; }
    String desSkillsLookup() { return prefix() + "/skills/lookup"; }
    String desSkillsAutoComplete() { return prefix() + "/skills/autoComplete"; }
    String desProfessionsGetTaxonomy(String format, String language) { return prefix() + "/professions/taxonomy?format="+ format +"&language="+ language; }
    String desProfessionsNormalize() { return prefix() + "/professions/normalize"; }
    String desProfessionsLookup() { return prefix() + "/professions/lookup"; }
    String desProfessionsAutoComplete() { return prefix() + "/professions/autoComplete"; }
    String desOntologySuggestSkills() { return prefix() + "/ontology/suggestSkills"; }
    String desOntologyCompareSkills() { return prefix() + "/ontology/compareSkills"; }
    String desOntologySuggestProfessions() { return prefix() + "/ontology/suggestProfessions"; }
    String desOntologyCompareSkillsToProfessions() { return prefix() + "/ontology/compareSkillsToProfessions"; }
}
