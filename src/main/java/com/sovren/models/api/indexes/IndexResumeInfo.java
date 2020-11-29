package com.sovren.models.api.indexes;

import com.sovren.models.resume.ParsedResume;

/**
 * Information for adding a single resume to an index as part of a 'batch upload'
 */
public class IndexResumeInfo extends IndexMultipleDocumentInfo {
    
    /**  A resume to index */
    public ParsedResume ResumeData;
}
