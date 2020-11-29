package com.sovren.models.api;

/**
* {@inheritDoc}
*/
public class ApiResponseInfo extends ApiResponseInfoLite {

    /** The id for a specific API transaction. Use this when contacting <a href="mailto:support@sovren.com">support@sovren.com</a> about issues.*/
    public String TransactionId;

    /** 
     * How long the transaction took on Sovren's server, in milliseconds.
     * <p>If the transaction takes longer to complete on the client side, that extra duration is solely network latency.</p>
    */
    public int TotalElapsedMilliseconds;

    /** The version of the parsing engine */
    public String EngineVersion;

    /** The version of the API */
    public String ApiVersion;

    /** Information about the customer who made the API call*/
    public AccountInfo CustomerDetails;
}
