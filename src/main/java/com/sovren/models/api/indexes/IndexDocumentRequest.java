package com.sovren.models.api.indexes;

import java.util.List;

/** Base class for 'index resume' or 'index job' request body */
public class IndexDocumentRequest {
    
    /** The user-defined tags the document should have */
    public List<String> UserDefinedTags;
}
