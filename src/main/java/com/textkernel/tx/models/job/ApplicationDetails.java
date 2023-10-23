// Copyright © 2023 Textkernel BV. All rights reserved.
// This file is provided for use by, or on behalf of, Textkernel licensees
// within the terms of their license of Sovren products or Textkernel customers
// within the Terms of Service pertaining to the Textkernel SaaS products.

package com.textkernel.tx.models.job;

import java.time.LocalDate;
import com.textkernel.tx.models.TxPrimitive;

/**
* An object containing details about the application process
*/
public class ApplicationDetails {
    
    /** Full text description of the application process*/
    public String ApplicationDescription;

    /** Full name of the main contact person for the application*/
    public String ContactPerson;

    /** Normalized phone of the organization with international calling prefix. Can contain multiple values (concatenated by comma)*/
    public String ContactPhone;

    /** Displayable email of the organization. Can contain multiple values (concatenated by comma)*/
    public String ContactEmail;

    /** Validated and normalized displayable website of the organization. Can contain multiple values (concatenated by comma)*/
    public String Website;
    
    /** Deadline to apply for the job*/
    public TxPrimitive<LocalDate> ApplicationDeadline;

    /** Date the job was posted*/
    public TxPrimitive<LocalDate> PostedDate;

    /** Any reference number found for the job application*/
    public String ReferenceNumber;
}
