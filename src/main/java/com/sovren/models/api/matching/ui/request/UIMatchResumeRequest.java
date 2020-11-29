package com.sovren.models.api.matching.ui.request;

import com.sovren.models.api.matching.MatchResumeRequest;

/**
 * The request body for generating a Sovren Matching UI session
 * */
public class UIMatchResumeRequest extends GenerateUIRequest<MatchResumeRequest> {
    public UIMatchResumeRequest(MatchResumeRequest request, MatchUISettings settings) {
        super(request, settings);
    }
}
