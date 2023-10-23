// Copyright © 2023 Textkernel BV. All rights reserved.
// This file is provided for use by, or on behalf of, Textkernel licensees
// within the terms of their license of Sovren products or Textkernel customers
// within the Terms of Service pertaining to the Textkernel SaaS products.

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
