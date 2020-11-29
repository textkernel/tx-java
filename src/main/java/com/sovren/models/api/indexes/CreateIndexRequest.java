package com.sovren.models.api.indexes;

import com.sovren.models.matching.IndexType;

/**
* Request body to create an index
*/
public class CreateIndexRequest {

    /** The type of documents this index will contain*/
    public IndexType IndexType;
}
