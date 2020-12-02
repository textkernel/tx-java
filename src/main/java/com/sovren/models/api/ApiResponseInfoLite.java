package com.sovren.models.api;

/**
* Information/metadata for an individual REST API call. 
* See https://docs.sovren.com/API/Rest#http-status-codes
*/
public class ApiResponseInfoLite {
    
    /** See https://docs.sovren.com/API/Rest#http-status-codes*/
    public String Code;

    /** A short human-readable description explaining the {@link #Code} value*/
    public String Message;

    public boolean isSuccess() {
        switch (Code) {
            case "Success":
            case "WarningsFoundDuringParsing":
            case "PossibleTruncationFromTimeout":
            case "SomeErrors":
                return true;
        }

        return false;
    }
}
