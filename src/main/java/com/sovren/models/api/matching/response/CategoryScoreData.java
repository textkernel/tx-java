package com.sovren.models.api.matching.response;

import java.util.List;

/**
* Details about the score for a specific category
*/
public class CategoryScoreData {
    
    /** An unweighted score from 0-100. This is the percentage match of this category.*/
    public double UnweightedScore;
    
    /** Detailed written explanation about each data point found or not found.*/
    public List<CategoryScoreEvidence> Evidence;
}
