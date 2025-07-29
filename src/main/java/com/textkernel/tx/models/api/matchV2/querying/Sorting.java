// Copyright © 2023 Textkernel BV. All rights reserved.
// This file is provided for use by, or on behalf of, Textkernel licensees
// within the terms of their license of Textkernel products or Textkernel customers
// within the Terms of Service pertaining to the Textkernel SaaS products.

package com.textkernel.tx.models.api.matchV2.querying;

/** Options to control sort order */
public class Sorting {
    /**
     * The field name. Two special "fields" exist: _reranker and _score. _score means sorting by the engine score,
     * _reranker means sorting by reranker score. For sorting with type _reranker a reranker needs to be configured.
     * A sorting on field _reranker cannot have subsortings on other fields and cannot have a custom order.
     */
    public String Field;

    /** 
     * Optional order of the sorting. The default behavior depends on the data type of the field: {@link SortOrder#DESCENDING}
     * for numeric and date types, {@link SortOrder#ASCENDING} for other types. For fields with location data type,
     * results will be sorted according to the distance to the {@link #ReferenceLocation} and only {@link SortOrder#ASCENDING} is supported.
     */
    public SortOrder Order;

    /**
     * Optional (required for fields with location data type specified) String representation of a location point in
     * the form of <code>LATITUDE LONGITUDE</code> (e.g. <code>53.3478 -6.2597</code>). This is used for distance sorting on a location field.
     */
    public String ReferenceLocation;
}
