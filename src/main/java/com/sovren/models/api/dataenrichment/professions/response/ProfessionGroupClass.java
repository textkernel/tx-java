// Copyright © 2020 Sovren Group, Inc. All rights reserved.
// This file is provided for use by, or on behalf of, Sovren licensees
// within the terms of their license of Sovren products or Sovren customers
// within the Terms of Service pertaining to the Sovren SaaS products.

package com.sovren.models.api.dataenrichment.professions.response;

/** The profession group class object */
public class ProfessionGroupClass extends ProfessionGroupClassInfo {
        /** The O*NET 2010 (deprecated) details of this profession. */
        public GroupOrClassInfo<String> Onet;
        /** The O*NET 2019 details of this profession. */
        public GroupOrClassInfo<String> Onet2019;
        /** The KLDB-2020 details of this profession. */
        public GroupOrClassInfo<String> Kldb2020;
        /** The UWV-BOC details of this profession. */
        public GroupOrClassInfo<String> UwvBoc;
        /** The UK-SOC-2010 details of this profession. */
        public GroupOrClassInfo<String> UkSoc2010;
        /** The ISCO-2008 details of this profession. */
        public GroupOrClassInfo<String> Isco;
}