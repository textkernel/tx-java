package com.sovren.models.resume.metadata;

import java.util.List;

/**
* Used by Sovren to redact PII
*/
public class ReservedData {
        
    /** All phone numbers found in the resume*/
    public List<String> Phones;
        
    /** All names found in the resume*/
    public List<String> Names;
        
    /** All email addresses found in the resume*/
    public List<String> EmailAddresses;
        
    /** All personal urls found in the resume*/
    public List<String> Urls;
        
    /** Any other PII that should be redacted*/
    public List<String> OtherData;
}
