// Copyright © 2020 Sovren Group, Inc. All rights reserved.
// This file is provided for use by, or on behalf of, Sovren licensees
// within the terms of their license of Sovren products or Sovren customers
// within the Terms of Service pertaining to the Sovren SaaS products.

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
