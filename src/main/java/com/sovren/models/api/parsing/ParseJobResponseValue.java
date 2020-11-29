package com.sovren.models.api.parsing;

import com.sovren.models.api.ApiResponse;
import com.sovren.models.job.ParsedJob;

/**
* The {@link ApiResponse#Value} from a Parse response
*/
public class ParseJobResponseValue extends BaseParseResponseValue {
    
    /** The main output from the Sovren Job Parser*/
    public ParsedJob JobData;
}
