// Copyright © 2023 Textkernel BV. All rights reserved.
// This file is provided for use by, or on behalf of, Textkernel licensees
// within the terms of their license of Textkernel products or Textkernel customers
// within the Terms of Service pertaining to the Textkernel SaaS products.

package com.textkernel.tx.models.resume;

import java.util.List;

/**
* All training info found in a resume
*/
public class TrainingHistory {

    /** The full text where we found all training history*/
    public String Text;

    /** Information about each training history we found*/
    public List<TrainingDetails> Trainings;
}
