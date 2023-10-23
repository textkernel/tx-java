// Copyright © 2020 Sovren Group, Inc. All rights reserved.
// This file is provided for use by, or on behalf of, Sovren licensees
// within the terms of their license of Sovren products or Sovren customers
// within the Terms of Service pertaining to the Sovren SaaS products.

package com.textkernel.tx.models.api.geocoding;

import com.textkernel.tx.models.api.indexes.IndexSingleDocumentInfo;

/** Request body for geocoding a document and then adding into an index */
public class GeocodeAndIndexRequest {
    
    /** Indicates whether or not the document should still be added to the index if the geocode request fails. */
    public boolean IndexIfGeocodeFails;

    /** Geocoding settings */
    public GeocodeOptionsBase GeocodeOptions;

    /** Where to index the resume */
    public IndexSingleDocumentInfo IndexingOptions;
}
