package com.sovren.models.api.bimetricscoring;

/**
* Request body for a 'BimetricScore' request
*/
public class BimetricScoreResumeRequest extends BimetricScoreRequest {

    /** 
     * The resume to use as the 'source' document for the bimetric score.
     * <p>All the target documents will be scored against this resume.
    */
    public ParsedResumeWithId SourceResume;
}
