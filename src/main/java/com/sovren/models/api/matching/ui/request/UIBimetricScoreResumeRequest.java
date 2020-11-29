package com.sovren.models.api.matching.ui.request;

import com.sovren.models.api.bimetricscoring.BimetricScoreResumeRequest;

/**
 * The request body for generating a Sovren Matching UI session
 * */
public class UIBimetricScoreResumeRequest extends GenerateUIRequest<BimetricScoreResumeRequest> {
    public UIBimetricScoreResumeRequest(BimetricScoreResumeRequest request, MatchUISettings settings) {
        super(request, settings);
    }
}
