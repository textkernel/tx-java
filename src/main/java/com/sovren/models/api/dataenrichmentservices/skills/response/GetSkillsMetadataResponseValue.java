package com.sovren.models.api.dataenrichmentservices.skills.response;

/** One entry in the {@link ApiResponse#Value} from a 'GetSkillsMetadata' response */
public class GetSkillsMetadataResponseValue {
    /** The version number of the skills service. */
    public String ServiceVersion;
    /** The date the taxonomy was released. */
    public String TaxonomyReleaseDate;
}
