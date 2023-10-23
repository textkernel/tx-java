// Copyright © 2020 Sovren Group, Inc. All rights reserved.
// This file is provided for use by, or on behalf of, Sovren licensees
// within the terms of their license of Sovren products or Sovren customers
// within the Terms of Service pertaining to the Sovren SaaS products.

package com.sovren.models.api.parsing;

import com.sovren.models.api.ApiResponseInfo;
import com.sovren.models.SovrenPrimitive;

/**
* Metadata about a parsing transaction
*/
public class ParsingMetadata {
    
    /** The version of the SaaS parser*/
    public String ParserVersion;
    
    /**
     * How long it took to parse the document, in milliseconds.
     * This is a subset of {@link ApiResponseInfo#TotalElapsedMilliseconds}
    */
    public int ElapsedMilliseconds;
    
    /** Whether or not the transaction timed out. If this is {@code true}, the returned data may be incomplete*/
    public boolean TimedOut;
    
    /** If {@link #TimedOut} is {@code true}, this is how much time was spent parsing before the timeout occurred */
    public SovrenPrimitive<Integer> TimedOutAtMilliseconds;

    /** For self-hosted customers only. The serial number of the current license being used for parsing. */
    public String LicenseSerialNumber;
}
