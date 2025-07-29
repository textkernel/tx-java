// Copyright © 2023 Textkernel BV. All rights reserved.
// This file is provided for use by, or on behalf of, Textkernel licensees
// within the terms of their license of Textkernel products or Textkernel customers
// within the Terms of Service pertaining to the Textkernel SaaS products.

package com.textkernel.tx.models.api.matchV2.querying;

import com.textkernel.tx.models.api.matchV2.MatchV2Environment;
import com.textkernel.tx.models.api.matchV2.documents.DocumentSource;

/**  Request body for a Match request */
public class MatchRequest {
    /** The target environment */
    public MatchV2Environment SearchAndMatchEnvironment;

    /** The options for the Match request */
    public Options Options;

    /** The query object that will be combined with the match query to drive the search. */
    public SearchQuery Query;

    /** The document to generate the search query from. */
    public DocumentSource SourceDocument;
}
