package com.sovren.models.resume.education;

import java.util.List;

/**
* Information about education history found on a resume
*/
public class EducationHistory {
    
    /** The highest degree obtained by a candidate*/
    public Degree HighestDegree;
    
    /** All of the education details listed on a resume*/
    public List<EducationDetails> EducationDetails;
}
