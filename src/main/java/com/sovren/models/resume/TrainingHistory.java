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
