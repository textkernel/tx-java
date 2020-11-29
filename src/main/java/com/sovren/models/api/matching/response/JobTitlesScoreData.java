package com.sovren.models.api.matching.response;

import java.util.List;

/**
* {@inheritDoc}
*/
public class JobTitlesScoreData extends CategoryScoreData {
    
    /** List of terms requested, but not found.*/
    public List<String> NotFound;
    
    /** List of job titles found in both documents.*/
    public List<FoundJobTitle> Found;
}
