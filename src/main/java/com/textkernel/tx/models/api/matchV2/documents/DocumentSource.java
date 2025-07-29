// Copyright © 2023 Textkernel BV. All rights reserved.
// This file is provided for use by, or on behalf of, Textkernel licensees
// within the terms of their license of Textkernel products or Textkernel customers
// within the Terms of Service pertaining to the Textkernel SaaS products.

package com.textkernel.tx.models.api.matchV2.documents;

/** Defines a document that can be used to generate a match query. */
public class DocumentSource {
    /** Specify what type of document is being passed to the match engine. */
    public DocumentType Type;

    /** Id of the document in the index to generate the query from. */
    public String Id;
}
