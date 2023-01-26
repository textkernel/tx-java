package com.sovren.models.api.dataenrichmentservices.professions.response;

/** One entry in the {@link ApiResponse#Value} from a 'GetProfessionsMetadata' response */
public class GetProfessionsMetadataResponseValue {
    /** The version number of the professions service. */
    public String ServiceVersion;
    /** The date the taxonomy was released. */
    public String TaxonomyReleaseDate;
}
