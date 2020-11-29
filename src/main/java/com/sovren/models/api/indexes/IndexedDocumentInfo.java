package com.sovren.models.api.indexes;

/**
* A small container for identifying an indexed document
*/
public class IndexedDocumentInfo {
    
    /** The id for the index that contains the document (case-insensitive).*/
    public String IndexId;
    
    /** The id of the document (case-insensitive)*/
    public String DocumentId;
}
