// Copyright © 2023 Textkernel BV. All rights reserved.
// This file is provided for use by, or on behalf of, Textkernel licensees
// within the terms of their license of Textkernel products or Textkernel customers
// within the Terms of Service pertaining to the Textkernel SaaS products.

package com.textkernel.tx.models.api.matching.ui.request;

import com.textkernel.tx.models.api.matching.MatchResumeRequest;

/**
 * The request body for generating a Matching UI session
 * */
public class UIMatchResumeRequest extends GenerateUIRequest<MatchResumeRequest> {
    public UIMatchResumeRequest(MatchResumeRequest request, MatchUISettings settings) {
        super(request, settings);
    }
}
