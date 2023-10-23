package com.sovren.models.dataenrichment;

/**
 * Object representing a profession concept with taxonomy version
 */
public class VersionedProfessionClassification<T> extends ProfessionClassification<T> {
    /**
     * The version of the profession taxonomy
     */
    public String Version;
}
