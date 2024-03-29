// Copyright © 2023 Textkernel BV. All rights reserved.
// This file is provided for use by, or on behalf of, Textkernel licensees
// within the terms of their license of Textkernel products or Textkernel customers
// within the Terms of Service pertaining to the Textkernel SaaS products.

package com.textkernel.tx.models;

import java.time.LocalDate;
/**
* the custom Date type that represents dates found in resumes/jobs. The following are common examples: 
*  <p>- Current, as in "July 2018 - current". See {@link TxDate#IsCurrentDate}
*  <p>- Year only, as in "2018 - 2020". {@link TxDate#FoundYear} will be true, {@link TxDate#FoundMonth} and {@link TxDate#FoundDay} will be false 
*  <p>- Year and month, as in "2018/06 - 2020/07". {@link TxDate#FoundYear} and {@link TxDate#FoundMonth} will be true, {@link TxDate#FoundDay} will be false 
*  <p>- Year/month/day, as in "5/4/2018 - 7/2/2020". {@link TxDate#FoundYear}, {@link TxDate#FoundMonth}, and {@link TxDate#FoundDay} will be true
*/
public class TxDate {
    /** The ISO 8601 (yyyy-MM-dd) date, if the day and/or month could not be found, they will be 01*/
    public LocalDate Date;

    /** {@code true} if this date represents '- current' and not an actual date*/
    public boolean IsCurrentDate;

    /** {@code true} if the year was found in the text, otherwise {@code false}*/
    public boolean FoundYear;

    /** {@code true} if the month was found in the text (eg: June 2020), otherwise {@code false} (eg: 2020) */
    public boolean FoundMonth;

    /** {@code true} if the day was found in the text (eg: June 7, 2020), otherwise {@code false} (eg: June 2020)*/
    public boolean FoundDay;
}
