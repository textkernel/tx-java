package com.sovren.models.api.matching.ui.request;

import com.sovren.models.api.bimetricscoring.BimetricScoreJobRequest;

/** {@inheritDoc} */
public class UIBimetricScoreJobRequest extends GenerateUIRequest<BimetricScoreJobRequest> {
    public UIBimetricScoreJobRequest(BimetricScoreJobRequest request, MatchUISettings settings) {
        super(request, settings);
    }
}
