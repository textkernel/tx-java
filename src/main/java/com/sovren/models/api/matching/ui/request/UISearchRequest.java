package com.sovren.models.api.matching.ui.request;

import com.sovren.models.api.matching.SearchRequest;

/**
 * The request body for generating a Sovren Matching UI session
 * */
public class UISearchRequest extends GenerateUIRequest<SearchRequest> {
    public UISearchRequest(SearchRequest request, MatchUISettings settings) {
        super(request, settings);
    }
}
