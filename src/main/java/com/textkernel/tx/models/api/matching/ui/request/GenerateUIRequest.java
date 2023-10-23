// Copyright © 2020 Sovren Group, Inc. All rights reserved.
// This file is provided for use by, or on behalf of, Sovren licensees
// within the terms of their license of Sovren products or Sovren customers
// within the Terms of Service pertaining to the Sovren SaaS products.

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
