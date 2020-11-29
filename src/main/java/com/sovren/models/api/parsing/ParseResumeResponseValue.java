package com.sovren.models.api.parsing;

import com.sovren.models.api.ApiResponse;
import com.sovren.models.resume.ParsedResume;

/**
* The {@link ApiResponse#Value} from a Parse response
*/
public class ParseResumeResponseValue extends BaseParseResponseValue {
    
    /** The main output from the Sovren Resume Parser*/
    public ParsedResume ResumeData;
    
    /**
     * Similar to {@link #ResumeData}, but with all of the 
     * Personally Identifiable Information (PII) redacted. For example,
     * this property will contain no {@link ParsedResume#ContactInformation}.
    */
    public ParsedResume RedactedResumeData;
}
