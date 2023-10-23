// Copyright © 2023 Textkernel BV. All rights reserved.
// This file is provided for use by, or on behalf of, Textkernel licensees
// within the terms of their license of Textkernel products or Textkernel customers
// within the Terms of Service pertaining to the Textkernel SaaS products.

package com.textkernel.tx.models.matching;

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
