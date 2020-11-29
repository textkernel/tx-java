package com.sovren.models.api.matching.ui.request;

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
