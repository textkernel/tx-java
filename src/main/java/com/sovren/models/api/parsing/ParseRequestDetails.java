package com.sovren.models.api.parsing;

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
