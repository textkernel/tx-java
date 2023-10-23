// Copyright © 2023 Textkernel BV. All rights reserved.
// This file is provided for use by, or on behalf of, Textkernel licensees
// within the terms of their license of Sovren products or Textkernel customers
// within the Terms of Service pertaining to the Textkernel SaaS products.

package com.textkernel.tx.models.api.indexes;

import com.textkernel.tx.models.api.ApiResponse;
import com.textkernel.tx.models.api.ApiResponseInfoLite;

/** One entry in the {@link ApiResponse#Value} from a 'index multiple documents' response */
public class IndexMultipleDocumentsResponseValue extends ApiResponseInfoLite {
    
    /** Id of the specific document represented in the response */
    public String DocumentId;
}
