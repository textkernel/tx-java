// Copyright © 2023 Textkernel BV. All rights reserved.
// This file is provided for use by, or on behalf of, Textkernel licensees
// within the terms of their license of Sovren products or Textkernel customers
// within the Terms of Service pertaining to the Textkernel SaaS products.

package com.textkernel.tx.models.api.matching.ui.request;

import com.textkernel.tx.models.api.bimetricscoring.BimetricScoreResumeRequest;

/**
 * The request body for generating a Sovren Matching UI session
 * */
public class UIBimetricScoreResumeRequest extends GenerateUIRequest<BimetricScoreResumeRequest> {
    public UIBimetricScoreResumeRequest(BimetricScoreResumeRequest request, MatchUISettings settings) {
        super(request, settings);
    }
}
