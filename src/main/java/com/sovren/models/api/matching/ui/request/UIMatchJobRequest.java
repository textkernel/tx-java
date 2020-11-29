package com.sovren.models.api.matching.ui.request;

import com.sovren.models.api.matching.MatchJobRequest;

/** {@inheritDoc} */
public class UIMatchJobRequest extends GenerateUIRequest<MatchJobRequest> {
    public UIMatchJobRequest(MatchJobRequest request, MatchUISettings settings) {
        super(request, settings);
    }
}
