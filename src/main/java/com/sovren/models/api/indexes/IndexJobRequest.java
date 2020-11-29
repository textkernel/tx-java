package com.sovren.models.api.indexes;

import com.sovren.models.job.ParsedJob;

/** Request body for an 'index job' request */
public class IndexJobRequest extends IndexDocumentRequest {
    
    /**  A job to index */
    public ParsedJob JobData;
}
