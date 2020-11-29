package com.sovren.models.resume.employment;

import java.util.List;

/**
* Work history found on a resume
*/
public class EmploymentHistory {
    
    /** A summary of all the work history with important calculated metadata*/
    public ExperienceSummary ExperienceSummary;
    
    /** A list of jobs/positions found on the resume*/
    public List<Position> Positions;
}
