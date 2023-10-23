// Copyright © 2020 Sovren Group, Inc. All rights reserved.
// This file is provided for use by, or on behalf of, Sovren licensees
// within the terms of their license of Sovren products or Sovren customers
// within the Terms of Service pertaining to the Sovren SaaS products.

package com.textkernel.tx.models.api.parsing;

/**
* Information about the parse request
*/
public class ParseRequestDetails {
    
    /** The configuration settings that were sent in the parse request*/
    public String ConfigurationString;
    
    /** The document id, if provided*/
    public String DocumentId;
    
    /** The length of the byte[] that was sent to be parsed*/
    public int FileBytesLength;
    
    /** The hash of the document that was parsed*/
    public String SovrenHash;
}
