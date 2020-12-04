// Copyright © 2020 Sovren Group, Inc. All rights reserved.
// This file is provided for use by, or on behalf of, Sovren licensees
// within the terms of their license of Sovren products or Sovren customers
// within the Terms of Service pertaining to the Sovren SaaS products.

package com.sovren.models.api.indexes;

import com.sovren.models.api.ApiResponse;
import com.sovren.models.api.ApiResponseInfoLite;

/** One entry in the {@link ApiResponse#Value} from a 'index multiple documents' response */
public class IndexMultipleDocumentsResponseValue extends ApiResponseInfoLite {
    
    /** Id of the specific document represented in the response */
    public String DocumentId;
}
