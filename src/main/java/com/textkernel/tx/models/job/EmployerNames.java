// Copyright © 2023 Textkernel BV. All rights reserved.
// This file is provided for use by, or on behalf of, Textkernel licensees
// within the terms of their license of Sovren products or Textkernel customers
// within the Terms of Service pertaining to the Textkernel SaaS products.

package com.textkernel.tx.models.job;

import java.util.List;

/**
* Names of employers found in a job description
*/
public class EmployerNames {
    
    /** The main/overall employer name*/
    public String MainEmployerName;
    
    /** All employer names found in a job description*/
    public List<String> EmployerName;
}
