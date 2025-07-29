// Copyright © 2023 Textkernel BV. All rights reserved.
// This file is provided for use by, or on behalf of, Textkernel licensees
// within the terms of their license of Textkernel products or Textkernel customers
// within the Terms of Service pertaining to the Textkernel SaaS products.

package com.textkernel.tx.models.api.matchV2.querying;

/** Options for synonym expansion in queries */
public enum SynonymExpansionMode {
    /** build the synonym map from the query only, expand the query. This is the default option. */
    QUERY,
    /** build synonym map from both query and query parts, expand the query, prune and expand the query parts. */
    QUERY_AND_QUERYPARTS,
    /** build synonym map from both query and query parts, expand the query, only prune the query parts. */
    QUERY_AND_SYNONYMMAP
}
