package com.sovren.models.api.matching.request;

import java.util.Optional;

/**
* Settings for pagination of results
*/
public class PaginationSettings {
    
    /** How many results to return*/
    public Optional<Integer> Take;
    
    /** How many results to skip. For example: (skip 5, take 10) means return results 6-15*/
    public Optional<Integer> Skip;
}
