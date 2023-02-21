package com.sovren.models.api.dataenrichment.professions.response;

/**
 * Object representing a profession concept with taxonomy version
 */
public class VersionedProfessionClassification<T> extends ProfessionClassification<T> {
    /**
     * The version of the profession taxonomy
     */
    public String Version;
}
