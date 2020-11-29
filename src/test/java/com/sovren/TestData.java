package com.sovren;

import com.sovren.models.Document;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;

public class TestData {
    private static String _resumeText = "John Wesson\n\nWork History\nSr. Software Developer at Sovren Inc.   07/2017 - 07/2018\n- used Javascript and ReactJS to make a web app";

    public static Document Resume = new Document(_resumeText.getBytes(StandardCharsets.UTF_8), LocalDate.now());

    private static String _resumeTextWithAddress = "John Wesson\n\n4544 McKinney Ave \nDallas, TX 75205\n\nWork History\nSr. Software Developer at Sovren Inc.   07/2017 - 07/2018\n- used Javascript and ReactJS to make a web app";

    public static Document ResumeWithAddress = new Document(_resumeTextWithAddress.getBytes(StandardCharsets.UTF_8), LocalDate.now());


    private static String _resumePersonalInformationText = "John Wesson\n\nPersonal Information\nBirthplace: Fort Worth, TX\nDOB: 5/5/1980\nDriver's License: TX98765432\nFather's Name: Janplop\nGender: M\nMarital Status: Single\nMother Tongue: English\nNationality: USA\nPassport Number: 5234098423478";

    public static Document ResumePersonalInformation = new Document(_resumePersonalInformationText.getBytes(StandardCharsets.UTF_8), LocalDate.now());


    private static String _jobOrderText = "Position Title: Sales Manager\n\nSkills:\n\tBudgeting\n\tAudit\n\tFinancial Statements";

    public static Document JobOrder = new Document(_jobOrderText.getBytes(StandardCharsets.UTF_8), LocalDate.now());

    private static String _jobOrderTextWithAddress = "\nPosition Title: Sales Manager\n\nCity:	  San Francisco\nState:	  CA\nZipcode:  45678\n\nSkills:\n\tBudgeting\n\tAudit\n\tFinancial Statements";

    public static Document JobOrderWithAddress = new Document(_jobOrderTextWithAddress.getBytes(StandardCharsets.UTF_8), LocalDate.now());

    private static String _jobOrderTextTech = "Position Title: Sr. Software Developer\n\nSkills:\n\tJavaScript\n\tReactJS";

    public static Document JobOrderTech = new Document(_jobOrderTextTech.getBytes(StandardCharsets.UTF_8), LocalDate.now());
}