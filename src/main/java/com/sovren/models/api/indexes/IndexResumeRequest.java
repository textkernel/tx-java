package com.sovren.models.api.indexes;

import com.sovren.models.resume.ParsedResume;

/** Request body for an 'index resume' request */
public class IndexResumeRequest extends IndexDocumentRequest {
    
    /**  A resume to index */
    public ParsedResume ResumeData;
}
