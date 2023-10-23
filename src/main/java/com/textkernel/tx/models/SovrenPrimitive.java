// Copyright © 2023 Textkernel BV. All rights reserved.
// This file is provided for use by, or on behalf of, Textkernel licensees
// within the terms of their license of Sovren products or Textkernel customers
// within the Terms of Service pertaining to the Textkernel SaaS products.

package com.textkernel.tx.models;

/**
* Represents a native type (int, boolean, etc) that can have a value or be null
*/
public class SovrenPrimitive<T> {
    /**
     * The value for this object
     */
    public T Value;
}
