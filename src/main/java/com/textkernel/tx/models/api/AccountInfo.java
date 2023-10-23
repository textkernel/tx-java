// Copyright © 2023 Textkernel BV. All rights reserved.
// This file is provided for use by, or on behalf of, Textkernel licensees
// within the terms of their license of Textkernel products or Textkernel customers
// within the Terms of Service pertaining to the Textkernel SaaS products.

package com.textkernel.tx.models.api;

/**
* Contains information about the account making the API call
*/
public class AccountInfo {
    
    /** The AccountId for the account*/
    public String AccountId;
        
    /** The customer name on the account*/
    public String Name;
        
    /** The client IP Address where the API call originated*/
    public String IPAddress;
        
    /** The region for the account, also known as the 'Data Center'*/
    public String Region;
        
    /** The number of credits remaining to be used by the account*/
    public double CreditsRemaining;
        
    /** The number of credits used by the account*/
    public double CreditsUsed;
        
    /** The number of requests that can be made at one time*/
    public int MaximumConcurrentRequests;
        
    /** The date that the current credits expire*/
    public String ExpirationDate;
}
