// Copyright © 2023 Textkernel BV. All rights reserved.
// This file is provided for use by, or on behalf of, Textkernel licensees
// within the terms of their license of Sovren products or Textkernel customers
// within the Terms of Service pertaining to the Textkernel SaaS products.

package com.textkernel.tx.models.resume.employment;

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
