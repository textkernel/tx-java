// Copyright © 2020 Sovren Group, Inc. All rights reserved.
// This file is provided for use by, or on behalf of, Sovren licensees
// within the terms of their license of Sovren products or Sovren customers
// within the Terms of Service pertaining to the Sovren SaaS products.

package com.sovren.models.api.dataenrichmentservices.professions.response;

public class LookupProfession {
    public LookupProfessionGroupClassInfo TkInfo;
    public LookupGroupOrClassInfo<string> Onet;
    public LookupGroupOrClassInfo<string> Isco;
    public LookupGroupOrClassInfo<string> Onet2019;
    public LookupGroupOrClassInfo<string> Kldb2020;
    public LookupGroupOrClassInfo<string> UwvBoc;
    public LookupGroupOrClassInfo<string> UkSoc2010;
}