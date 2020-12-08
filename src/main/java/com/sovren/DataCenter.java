// Copyright © 2020 Sovren Group, Inc. All rights reserved.
// This file is provided for use by, or on behalf of, Sovren licensees
// within the terms of their license of Sovren products or Sovren customers
// within the Terms of Service pertaining to the Sovren SaaS products.

package com.sovren;

/** Use either {@link DataCenter#US} or {@link DataCenter#EU}*/
public class DataCenter {
    
    /** Represents the Sovren US datacenter. You can find out which datacenter your account is in at https://portal.sovren.com*/
    public static DataCenter US = new DataCenter("https://rest.resumeparsing.com", "v10", false);

    /** Represents the Sovren EU datacenter. You can find out which datacenter your account is in at https://portal.sovren.com*/
    public static DataCenter EU = new DataCenter("https://eu-rest.resumeparsing.com", "v10", false);

    String Root;
    String Version;
    boolean IsSovrenSaaS;

    protected DataCenter(String root, String version, boolean isSaaS) {
        Root = root;
        Version = version;
        IsSovrenSaaS = isSaaS;
    }

    /**
     * Create a DataCenter for a self-hosted instance
     * @param endpoint The URL of your self-hosted instance
     */
    public DataCenter(String endpoint) {
        this(endpoint, null, false);
    }
}
