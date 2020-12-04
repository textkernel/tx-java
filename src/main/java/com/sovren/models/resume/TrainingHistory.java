// Copyright © 2020 Sovren Group, Inc. All rights reserved.
// This file is provided for use by, or on behalf of, Sovren licensees
// within the terms of their license of Sovren products or Sovren customers
// within the Terms of Service pertaining to the Sovren SaaS products.

package com.sovren.models.resume;

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
