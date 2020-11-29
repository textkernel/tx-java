package com.sovren.models.api.matching.request;

import java.util.List;

/**
* Base class for match/search requests
*/
public abstract class SearchMatchRequestBase {
    
    /** The ids of the indexes in which you want to find results (case-insensitive).*/
    public List<String> IndexIdsToSearchInto;
    
    /** The settings to use during searching/matching queries*/
    public SearchMatchSettings Settings;
    
    /** Required criteria for the result set.*/
    public FilterCriteria FilterCriteria;
}
