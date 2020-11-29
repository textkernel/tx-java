package com.sovren.models.api.indexes;

import com.sovren.models.job.ParsedJob;

/**
 * Information for adding a single job to an index as part of a 'batch upload'
 */
public class IndexJobInfo extends IndexMultipleDocumentInfo {
    
    /**  A job to index */
    public ParsedJob JobData;
}
