// Copyright © 2023 Textkernel BV. All rights reserved.
// This file is provided for use by, or on behalf of, Textkernel licensees
// within the terms of their license of Sovren products or Textkernel customers
// within the Terms of Service pertaining to the Textkernel SaaS products.

package com.textkernel.tx.models.resume.military;

import com.textkernel.tx.models.TxDate;

/**
* Information about military post/job listed on a resume
*/
public class MilitaryDetails {

    /** The country that the military belongs to*/
    public String Country;

    /** The branch/name/rank for this post/job*/
    public MilitaryService Service;

    /** The start date for this post/job*/
    public TxDate StartDate;

    /** The end date for this post/job*/
    public TxDate EndDate;

    /** The full text where Sovren found this military post/job in the resume*/
    public String FoundInContext;
}
