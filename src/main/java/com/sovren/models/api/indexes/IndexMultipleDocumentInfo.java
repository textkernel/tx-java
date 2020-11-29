package com.sovren.models.api.indexes;

import java.util.List;

/**
 * Information for adding a single document to an index as part of a 'batch upload'
 */
public class IndexMultipleDocumentInfo {
    
    /**
     * The id to assign to the new document. This is restricted to alphanumeric with dashes and underscores. 
     * All values will be converted to lower-case.
    */
    public String DocumentId;

    /** The user-defined tags the document should have */
    public List<String> UserDefinedTags;
}
