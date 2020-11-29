package com.sovren.models.api.indexes;

import java.util.List;

/** Request body for an 'index multiple jobs' request */
public class IndexMultipleJobsRequest {
    
    /** The jobs to add into the index */
    public List<IndexJobInfo> Jobs;
}
