package com.sovren.models.api.indexes;

import com.sovren.models.api.ApiResponse;
import com.sovren.models.api.ApiResponseInfoLite;

/** One entry in the {@link ApiResponse#Value} from a 'index multiple documents' response */
public class IndexMultipleDocumentsResponseValue extends ApiResponseInfoLite {
    
    /** Id of the specific document represented in the response */
    public String DocumentId;
}
