// Copyright © 2023 Textkernel BV. All rights reserved.
// This file is provided for use by, or on behalf of, Textkernel licensees
// within the terms of their license of Textkernel products or Textkernel customers
// within the Terms of Service pertaining to the Textkernel SaaS products.

package com.textkernel.tx;

/** Use either {@link DataCenter#US} or {@link DataCenter#EU}*/
public class DataCenter {
    
    /** Represents the US datacenter. You can find out which datacenter your account is in at https://cloud.textkernel.com/tx/console*/
    public static DataCenter US = new DataCenter("https://api.us.textkernel.com/tx", "v10", true);

    /** Represents the EU datacenter. You can find out which datacenter your account is in at https://cloud.textkernel.com/tx/console*/
    public static DataCenter EU = new DataCenter("https://api.eu.textkernel.com/tx", "v10", true);
	
	/** Represents the AU datacenter. You can find out which datacenter your account is in at https://cloud.textkernel.com/tx/console*/
    public static DataCenter AU = new DataCenter("https://api.au.textkernel.com/tx", "v10", true);

    String Root;
    String Version;
    boolean IsSaaS;

    protected DataCenter(String root, String version, boolean isSaaS) {
        Root = root;
        Version = version;
        IsSaaS = isSaaS;
    }

    /**
     * Create a DataCenter for a self-hosted instance
     * @param endpoint The URL of your self-hosted instance
     */
    public DataCenter(String endpoint) {
        this(endpoint, null, false);
    }
}
