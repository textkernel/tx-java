package com.sovren.models.api.indexes;

import com.sovren.models.job.ParsedJob;

/** {@inheritDoc} */
public class IndexJobRequest extends IndexDocumentRequest {
    
    /**  A job to index */
    public ParsedJob JobData;
}
