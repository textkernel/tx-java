package com.sovren.models.api.bimetricscoring;

/**
* Request body for a 'BimetricScore' request
*/
public class BimetricScoreJobRequest extends BimetricScoreRequest {

    /** 
     * The job to use as the 'source' document for the bimetric score.
     * <p>All the target documents will be scored against this job.
    */
    public ParsedJobWithId SourceJob;
}
