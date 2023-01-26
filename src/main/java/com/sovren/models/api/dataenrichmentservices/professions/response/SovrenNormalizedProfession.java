package com.sovren.models.api.dataenrichmentservices.professions.response;

/** A Sovren normalized profession object. */
public class SovrenNormalizedProfession extends LookupProfession {
    /** A value from [0 - 1] indicating how well the input job title matched to the normalized profession. */
    public float Confidence;
}
