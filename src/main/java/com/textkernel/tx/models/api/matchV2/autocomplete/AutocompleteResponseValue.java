// Copyright © 2023 Textkernel BV. All rights reserved.
// This file is provided for use by, or on behalf of, Textkernel licensees
// within the terms of their license of Textkernel products or Textkernel customers
// within the Terms of Service pertaining to the Textkernel SaaS products.

package com.textkernel.tx.models.api.matchV2.autocomplete;

import java.util.List;

import com.textkernel.tx.models.api.ApiResponse;

/** The {@link ApiResponse#Value} for an Autocomplete response */
public class AutocompleteResponseValue {
    /** A list of suggested completions */
    public List<Completion> Return;
}
