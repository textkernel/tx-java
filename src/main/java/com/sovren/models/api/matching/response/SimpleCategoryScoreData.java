package com.sovren.models.api.matching.response;

import java.util.List;

/**
* {@inheritDoc}
*/
public class SimpleCategoryScoreData extends CategoryScoreData {
    
    /** List of terms found in both source and target documents*/
    public List<String> Found;
    
    /** List of terms requested but not found*/
    public List<String> NotFound;
}
