package com.sovren.models.matching;

/**
* A document index to hold resumes or jobs
*/
public class Index {

    /** The account id of the owner for this index */
    public String OwnerId;

    /** The name/id of this index*/
    public String Name;

    /** The type of document in this index*/
    public IndexType IndexType;
}
