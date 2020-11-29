package com.sovren.models.api.matching.ui.request;

import com.sovren.models.api.matching.MatchResumeRequest;

/** {@inheritDoc} */
public class UIMatchResumeRequest extends GenerateUIRequest<MatchResumeRequest> {
    public UIMatchResumeRequest(MatchResumeRequest request, MatchUISettings settings) {
        super(request, settings);
    }
}
