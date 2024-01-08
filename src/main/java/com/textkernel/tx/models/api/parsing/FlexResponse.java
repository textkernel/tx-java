// Copyright © 2023 Textkernel BV. All rights reserved.
// This file is provided for use by, or on behalf of, Textkernel licensees
// within the terms of their license of Textkernel products or Textkernel customers
// within the Terms of Service pertaining to the Textkernel SaaS products.


package com.textkernel.tx.models.api.parsing;

import java.util.List;

/**
 * Information about the FlexRequests transaction, if any were provided.
 */
public class FlexResponse {
    
    /** See https://developer.textkernel.com/tx-platform/v10/overview/#http-status-codes*/
    public String Code;

    /** A short human-readable description explaining the {@link #Code} value*/
    public String Message;
    
    /**
     * Responses to FlexRequests
     */
    public List<FlexResponseItem> Responses;

    public boolean isSuccess() {
        switch (Code) {
            case "Success":
                return true;
        }

        return false;
    }
}