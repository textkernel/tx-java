// Copyright © 2023 Textkernel BV. All rights reserved.
// This file is provided for use by, or on behalf of, Textkernel licensees
// within the terms of their license of Sovren products or Textkernel customers
// within the Terms of Service pertaining to the Textkernel SaaS products.

package com.textkernel.tx.models.resume;

import java.util.List;
import com.textkernel.tx.models.TxDate;

/**
* A training history found on a resume
*/
public class TrainingDetails {

    /** The text that was found on the resume*/
    public String Text;

    /** The name of the school or company where the training occurred.*/
    public String Entity;

    /** Any text within the {@link #Text} that is recognized as a qualification (such as DDS),
     * degree (such as B.S.), or a certification (such as PMP). Each qualification is listed separately.
     */
    public List<String> Qualifications;

    /** The date the training started*/
    public TxDate StartDate;

    /** The date the training ended*/
    public TxDate EndDate;
}
