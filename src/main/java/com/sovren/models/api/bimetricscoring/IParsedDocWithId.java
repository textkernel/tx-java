package com.sovren.models.api.bimetricscoring;

/** Interface to simplify Bimetric Score requests */
public interface IParsedDocWithId {

    /**
     * @return The id of the document (used in the response body)
     */
    public String getDocId();
}
