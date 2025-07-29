// Copyright © 2023 Textkernel BV. All rights reserved.
// This file is provided for use by, or on behalf of, Textkernel licensees
// within the terms of their license of Textkernel products or Textkernel customers
// within the Terms of Service pertaining to the Textkernel SaaS products.

package com.textkernel.tx.models.api.matchV2.documents;

import java.util.List;

import com.textkernel.tx.models.api.matchV2.MatchV2Environment;

/** Most SearchMatchV2 requests have Roles */
public class AddDocumentRequest {
    /** The roles associated with the request. Defaults to ["All"] if none are provided. */
    public List<String> Roles;

    /** The target environment */
    public MatchV2Environment SearchAndMatchEnvironment;
}
