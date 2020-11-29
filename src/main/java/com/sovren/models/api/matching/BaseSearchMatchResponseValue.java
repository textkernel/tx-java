package com.sovren.models.api.matching;

import java.util.List;

/**
* Base class for searches/matches response values
*/
public class BaseSearchMatchResponseValue<T> {

    /** The list of matches for the search/match*/
    public List<T> Matches;

    /** The number of results returned in this response*/
    public int CurrentCount;
    
    /** The total number of results that fit the query/criteria*/
    public int TotalCount;
}
