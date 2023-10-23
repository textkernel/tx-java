// Copyright © 2020 Sovren Group, Inc. All rights reserved.
// This file is provided for use by, or on behalf of, Sovren licensees
// within the terms of their license of Sovren products or Sovren customers
// within the Terms of Service pertaining to the Sovren SaaS products.

package com.textkernel.tx.models.resume.contactinfo;

/**
* A name broken into its constituent parts
*/
public class PersonName {

    /** The full name in a standard format*/
    public String FormattedName;

    /** A prefix for a name, such as Dr.*/
    public String Prefix;

    /** The given (first) name*/
    public String GivenName;

    /** The middle name or initial*/
    public String MiddleName;

    /** The nickname/moniker, this is rarely populated */
    public String Moniker;

    /** The family (last) name*/
    public String FamilyName;

    /** A suffix for a name, such as Jr. or III*/
    public String Suffix;
}
