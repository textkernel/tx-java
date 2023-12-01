package com.textkernel.tx.models.dataenrichment;

/**
 * A normalized profession from the Professions Taxonomy
 */
public class Profession extends BasicProfession {
        /**
        * The group which this profession belongs to.
        */
        public ProfessionClassification<Integer> Group;
        /**
        /// The class which this profession belongs to.
        */
        public ProfessionClassification<Integer> Class;
        /**
        /// The O*NET 2010 (deprecated) details of this profession.
        */
        public ProfessionClassification<String> Onet;
        /**
        /// The ISCO-2008 details of this profession.
        */
        public ProfessionClassification<String> Isco;
        /**
        /// The O*NET 2019 details of this profession.
        */
        public ProfessionClassification<String> Onet2019;
        /**
        /// The KLDB-2020 details of this profession.
        */
        public ProfessionClassification<String> Kldb2020;
        /**
        /// The UWV-BOC details of this profession.
        */
        public ProfessionClassification<String> UwvBoc;
        /**
        /// The UK-SOC-2010 details of this profession.
        */
        public ProfessionClassification<String> UkSoc2010;
}