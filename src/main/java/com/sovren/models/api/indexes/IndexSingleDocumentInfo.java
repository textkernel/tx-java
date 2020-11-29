package com.sovren.models.api.indexes;

/**
* Information for adding a document to an index
*/
public class IndexSingleDocumentInfo extends IndexMultipleDocumentInfo {
    
    /** The id for the index where the document should be added (case-insensitive).*/
    public String IndexId;
}
