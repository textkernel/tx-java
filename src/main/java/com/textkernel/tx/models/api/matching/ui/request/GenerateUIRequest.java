// Copyright © 2023 Textkernel BV. All rights reserved.
// This file is provided for use by, or on behalf of, Textkernel licensees
// within the terms of their license of Sovren products or Textkernel customers
// within the Terms of Service pertaining to the Textkernel SaaS products.

package com.textkernel.tx.models.api.matching.ui.request;

/**
 * The request body for generating a Sovren Matching UI session
 * @param <T> The type of search/match to be performed in the session
 * */
public class GenerateUIRequest<T> extends MatchUISettings {
    /** The SaaS request that defines the match/search.*/
    public T SaasRequest;

    public GenerateUIRequest(T saasRequest, MatchUISettings settings) {
        SaasRequest = saasRequest;
        copyFrom(settings);
    }
}
