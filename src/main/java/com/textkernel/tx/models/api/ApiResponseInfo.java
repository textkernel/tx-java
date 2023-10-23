// Copyright © 2023 Textkernel BV. All rights reserved.
// This file is provided for use by, or on behalf of, Textkernel licensees
// within the terms of their license of Textkernel products or Textkernel customers
// within the Terms of Service pertaining to the Textkernel SaaS products.

package com.textkernel.tx.models.api;

/**
* Information/metadata for an individual REST API call. 
* See https://developer.textkernel.com/Sovren/v10/overview/#http-status-codes
*/
public class ApiResponseInfo extends ApiResponseInfoLite {

    /** The id for a specific API transaction. Use this when contacting <a href="mailto:service@textkernel.com">service@textkernel.com</a> about issues.*/
    public String TransactionId;

    /** 
     * How long the transaction took on the server, in milliseconds.
     * <p>If the transaction takes longer to complete on the client side, that extra duration is solely network latency.
    */
    public int TotalElapsedMilliseconds;

    /** The version of the parsing engine */
    public String EngineVersion;

    /** The version of the API */
    public String ApiVersion;
    
    /** How many credits the customer was charged for this transaction*/
    public double CreditsUsed;

    /** Information about the customer who made the API call*/
    public AccountInfo CustomerDetails;
}
