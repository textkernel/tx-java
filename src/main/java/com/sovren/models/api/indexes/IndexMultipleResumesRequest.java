package com.sovren.models.api.indexes;

import java.util.List;

/** Request body for an 'index multiple resumes' request */
public class IndexMultipleResumesRequest {
    
    /** The resumes to add into the index */
    public List<IndexResumeInfo> Resumes;
}
