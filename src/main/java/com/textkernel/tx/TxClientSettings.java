// Copyright © 2023 Textkernel BV. All rights reserved.
// This file is provided for use by, or on behalf of, Textkernel licensees
// within the terms of their license of Textkernel products or Textkernel customers
// within the Terms of Service pertaining to the Textkernel SaaS products.

package com.textkernel.tx;

import java.util.List;

/** The SDK client to perform Tx API calls. */
public class TxClientSettings extends EnvironmentSettings {
    /**
     * The Account ID for your account. Found at https://cloud.textkernel.com/tx/console
     */
    public String AccountId;
    
    /**
     * The Service Key for your account. Found at https://cloud.textkernel.com/tx/console
     */
    public String ServiceKey;
    
    /**
     * Optional tags to use to track API usage for your account
     */
    public List<String> TrackingTags;
}
