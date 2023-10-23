// Copyright © 2020 Sovren Group, Inc. All rights reserved.
// This file is provided for use by, or on behalf of, Sovren licensees
// within the terms of their license of Sovren products or Sovren customers
// within the Terms of Service pertaining to the Sovren SaaS products.

package com.textkernel.tx.models.api;

/**
* Information/metadata for an individual REST API call. 
* See https://sovren.com/technical-specs/latest/rest-api/overview/#http-status-codes
*/
public class ApiResponseInfoLite {
    
    /** See https://sovren.com/technical-specs/latest/rest-api/overview/#http-status-codes*/
    public String Code;

    /** A short human-readable description explaining the {@link #Code} value*/
    public String Message;

    public boolean isSuccess() {
        switch (Code) {
            case "Success":
            case "WarningsFoundDuringParsing":
            case "PossibleTruncationFromTimeout":
            case "SomeErrors":
                return true;
        }

        return false;
    }
}
