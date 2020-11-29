package com.sovren.models.api.matching.ui.request;

import com.sovren.models.api.matching.request.MatchByDocumentIdOptions;

/**
 * The request body for generating a Sovren Matching UI session
 * */
public class UIMatchByDocumentIdOptions extends GenerateUIRequest<MatchByDocumentIdOptions> {
    public UIMatchByDocumentIdOptions(MatchByDocumentIdOptions options, MatchUISettings settings) {
        super(options, settings);
    }
}
