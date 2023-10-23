// Copyright © 2020 Sovren Group, Inc. All rights reserved.
// This file is provided for use by, or on behalf of, Sovren licensees
// within the terms of their license of Sovren products or Sovren customers
// within the Terms of Service pertaining to the Sovren SaaS products.

package com.textkernel.tx.models.api.matching.ui.request;

import com.textkernel.tx.models.api.matching.MatchJobRequest;

/**
 * The request body for generating a Sovren Matching UI session
 * */
public class UIMatchJobRequest extends GenerateUIRequest<MatchJobRequest> {
    public UIMatchJobRequest(MatchJobRequest request, MatchUISettings settings) {
        super(request, settings);
    }
}
