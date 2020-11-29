package com.sovren.models.api.matching.ui.request;

import com.sovren.models.api.bimetricscoring.BimetricScoreJobRequest;

/**
 * The request body for generating a Sovren Matching UI session
 * */
public class UIBimetricScoreJobRequest extends GenerateUIRequest<BimetricScoreJobRequest> {
    public UIBimetricScoreJobRequest(BimetricScoreJobRequest request, MatchUISettings settings) {
        super(request, settings);
    }
}
