// Copyright © 2023 Textkernel BV. All rights reserved.
// This file is provided for use by, or on behalf of, Textkernel licensees
// within the terms of their license of Textkernel products or Textkernel customers
// within the Terms of Service pertaining to the Textkernel SaaS products.

package com.textkernel.tx.models.api.formatter;

import com.textkernel.tx.models.api.ApiResponse;

/**
* The {@link ApiResponse#Value} from a Format Resume response
*/
public class FormatResumeResponseValue {
    /**
     * The formatted resume document (either PDF or DOCX).
     * This is a {@code byte[]} as a Base64-encoded string. You can use
     * {@link java.util.Base64.Decoder#decode(String)} to get a {@code byte[]} to save to disk.
     */
    public String DocumentAsBase64String;
}
