// Copyright © 2023 Textkernel BV. All rights reserved.
// This file is provided for use by, or on behalf of, Textkernel licensees
// within the terms of their license of Textkernel products or Textkernel customers
// within the Terms of Service pertaining to the Textkernel SaaS products.

package com.textkernel.tx.models.api.matchV2.autocomplete;

import com.textkernel.tx.models.api.matchV2.MatchV2Environment;

/** The request body for an Autocomplete request */
public class AutocompleteRequest<T> {
    /** The field to retrieve completions for */
    public T Field;

    /** The user-typed input string. */
    public String Input;

    /** Which environment to target */
    public MatchV2Environment SearchAndMatchEnvironment;

    /** Optional comma-separated 2-letter ISO-639-1 language codes from the supported languages. The first language is used for field label translations. */
    public String Language;
}
