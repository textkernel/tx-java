package com.sovren.models.api.matching;

import com.sovren.models.api.matching.request.SearchMatchRequestBase;
import com.sovren.models.api.matching.request.PaginationSettings;

/**
* Request body for a search request
*/
public class SearchRequest extends SearchMatchRequestBase {

    /** Used to choose which results to return from the list.*/
    public PaginationSettings PaginationSettings;
}
