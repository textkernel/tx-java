// Copyright © 2020 Sovren Group, Inc. All rights reserved.
// This file is provided for use by, or on behalf of, Sovren licensees
// within the terms of their license of Sovren products or Sovren customers
// within the Terms of Service pertaining to the Sovren SaaS products.

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
