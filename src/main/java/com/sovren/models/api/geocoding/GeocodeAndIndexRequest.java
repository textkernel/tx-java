package com.sovren.models.api.geocoding;

import com.sovren.models.api.indexes.IndexSingleDocumentInfo;

/** Request body for geocoding a document and then adding into an index */
public class GeocodeAndIndexRequest {
    
    /** Indicates whether or not the document should still be added to the index if the geocode request fails. */
    public boolean IndexIfGeocodeFails;

    /** Geocoding settings */
    public GeocodeOptionsBase GeocodeOptions;

    /** Where to index the resume */
    public IndexSingleDocumentInfo IndexingOptions;
}
