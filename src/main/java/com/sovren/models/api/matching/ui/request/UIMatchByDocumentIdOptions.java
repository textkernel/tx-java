package com.sovren.models.api.matching.ui.request;

import com.sovren.models.api.matching.request.MatchByDocumentIdOptions;

/** {@inheritDoc}*/
public class UIMatchByDocumentIdOptions extends GenerateUIRequest<MatchByDocumentIdOptions> {
    public UIMatchByDocumentIdOptions(MatchByDocumentIdOptions options, MatchUISettings settings) {
        super(options, settings);
    }
}
