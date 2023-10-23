# Basic Resume Parsing Example

```java
import com.textkernel.tx.*;
import com.textkernel.tx.exceptions.SovrenException;
import com.textkernel.tx.models.Document;
import com.textkernel.tx.models.SovrenDate;
import com.textkernel.tx.models.api.parsing.ParseOptions;
import com.textkernel.tx.models.api.parsing.ParseRequest;
import com.textkernel.tx.models.api.parsing.ParseResumeResponse;
import com.textkernel.tx.models.resume.PersonalAttributes;
import com.textkernel.tx.models.resume.contactinfo.ContactInformation;
import com.textkernel.tx.models.resume.contactinfo.WebAddress;
import com.textkernel.tx.models.resume.contactinfo.WebAddressType;
import com.textkernel.tx.models.resume.education.EducationDetails;
import com.textkernel.tx.models.resume.education.EducationHistory;
import com.textkernel.tx.models.resume.employment.EmploymentHistory;
import com.textkernel.tx.models.resume.employment.Position;
import java.io.IOException;
import java.time.format.DateTimeFormatter;

public class ParsingExample {
    public static void main(String[] args) throws IOException {
        SovrenClient client = new SovrenClient("12345678", "abcdefghijklmnopqrstuvwxyz", DataCenter.US);
        
        //A Document is an unparsed File (PDF, Word Doc, etc)
        Document doc = new Document("resume.docx");
    
        //when you create a ParseRequest, you can specify many configuration settings
        //in the ParseOptions. See https://developer.textkernel.com/Sovren/v10/resume-parser/api/
        ParseRequest request = new ParseRequest(doc, new ParseOptions());
    
        try {
            ParseResumeResponse response = client.parseResume(request);
            //if we get here, it was 200-OK and all operations succeeded
    
            //now we can use the response from Sovren to output some of the data from the resume
            printBasicResumeInfo(response);
        }
        catch (SovrenException e) {
            //the document could not be parsed, always try/catch for SovrenExceptions when using SovrenClient
            System.out.println("Code: " + e.HttpStatusCode + ", Error: " + e.SovrenErrorCode + ", Message: " + e.getMessage());
        }
    }
    
    static void printBasicResumeInfo(ParseResumeResponse response) {
        if (response != null && response.Value != null && response.Value.ResumeData != null) {
            printContactInfo(response.Value.ResumeData.ContactInformation);
            printPersonalInfo(response.Value.ResumeData.PersonalAttributes);
            printWorkHistory(response.Value.ResumeData.EmploymentHistory);
            printEducation(response.Value.ResumeData.Education);
        }
    }
    
    static void printHeader(String headerName) {
        System.out.println(System.lineSeparator() + System.lineSeparator() + "--------------- " + headerName + " ---------------");
    }
    
    static void printContactInfo(ContactInformation contactInfo) {
        //general contact information (only some examples listed here, there are many others)
        if (contactInfo != null) {
            printHeader("CONTACT INFORMATION");
            if (contactInfo.CandidateName != null)
                System.out.println("Name: " + contactInfo.CandidateName.FormattedName);
            if (contactInfo.EmailAddresses != null && contactInfo.EmailAddresses.size() > 0)
                System.out.println("Email: " + contactInfo.EmailAddresses.get(0));
            if (contactInfo.Telephones != null && contactInfo.Telephones.size() > 0)
                System.out.println("Phone: " + contactInfo.Telephones.get(0));
            
            if (contactInfo.Location != null) {
                System.out.println("City: " + contactInfo.Location.Municipality);
                if (contactInfo.Location.Regions != null && contactInfo.Location.Regions.size() > 0)
                    System.out.println("Region: " + contactInfo.Location.Regions.get(0));
                System.out.println("Country: " + contactInfo.Location.CountryCode);
            }
            
            if (contactInfo.WebAddresses != null) {
                contactInfo.WebAddresses
                        .stream()
                        .filter(w -> w.Type.equals(WebAddressType.LinkedIn.Value))
                        .findFirst()
                        .ifPresent(w -> System.out.println("LinkedIn: " + w.Address));
            }
        }
    }
    
    static void printPersonalInfo(PersonalAttributes personalInfo) {
        //personal information (only some examples listed here, there are many others)
        if (personalInfo != null) {
            printHeader("PERSONAL INFORMATION");
            
            if (personalInfo.DateOfBirth != null)
                System.out.println("Date of Birth: " + getSovrenDateAsString(personalInfo.DateOfBirth));
            if (personalInfo.DrivingLicense != null)
                System.out.println("Driving License: " + personalInfo.DrivingLicense);
            if (personalInfo.Nationality != null)
                System.out.println("Nationality: " + personalInfo.Nationality);
            if (personalInfo.VisaStatus != null)
                System.out.println("Visa Status: " + personalInfo.VisaStatus);
        }
    }
    
    static void printWorkHistory(EmploymentHistory employment) {
        //basic work history display
        if (employment != null) {
            printHeader("EXPERIENCE SUMMARY");

            if (employment.ExperienceSummary != null) {
                System.out.println("Years Experience: " + Math.round(employment.ExperienceSummary.MonthsOfWorkExperience / 12.0));
                System.out.println("Avg Years Per Employer: " + Math.round(employment.ExperienceSummary.AverageMonthsPerEmployer / 12.0));
                System.out.println("Years Management Experience: " + Math.round(employment.ExperienceSummary.MonthsOfManagementExperience / 12.0));
            }
            
            for (Position position : employment.Positions) {
                System.out.println(System.lineSeparator() + "POSITION '" + position.Id + "'");
                if (position.Employer != null && position.Employer.Name != null)
                    System.out.println("Employer: " + position.Employer.Name.Normalized);
                if (position.JobTitle != null)
                    System.out.println("Title: " + position.JobTitle.Normalized);
                System.out.println("Date Range: " + getSovrenDateAsString(position.StartDate) + " - " + getSovrenDateAsString(position.EndDate));
            }
        }
    }
    
    static void printEducation(EducationHistory education) {
        //basic education display
        if (education != null) {
            printHeader("EDUCATION SUMMARY");

            if (education.HighestDegree != null && education.HighestDegree.Name != null) {
                System.out.println("Highest Degree: " + education.HighestDegree.Name.Normalized);
            }
            
            for (EducationDetails edu : education.EducationDetails) {
                System.out.println(System.lineSeparator() + "EDUCATION '" + edu.Id + "'");
                if (edu.SchoolName != null)
                    System.out.println("School: " + edu.SchoolName.Normalized);
                if (edu.Degree != null && edu.Degree.Name != null)
                    System.out.println("Degree: " + edu.Degree.Name.Normalized);
                if (edu.Majors != null)
                    System.out.println("Focus: " + String.join(", ", edu.Majors));
                if (edu.GPA != null)
                    System.out.println("GPA: " + edu.GPA.NormalizedScore + "/1.0 (" + edu.GPA.Score + "/" + edu.GPA.MaxScore + ")");
                String endDateRepresents = edu.Graduated != null && edu.Graduated.Value ? "Graduated" : "Last Attended";
                System.out.println(endDateRepresents + ": " + getSovrenDateAsString(edu.EndDate));
            }
        }
    }
    
    static String getSovrenDateAsString(SovrenDate date) {
        //a SovrenDate represents a date found on a resume, so it can either be 
        //'current', as in "July 2018 - current"
        //a year, as in "2018 - 2020"
        //a year and month, as in "2018/06 - 2020/07"
        //a year/month/day, as in "5/4/2018 - 7/2/2020"
    
        if (date == null) return "";
        if (date.IsCurrentDate) return "current";
    
        String format = "yyyy";
    
        if (date.FoundMonth) {
            format += "-MM";//only print the month if it was actually found on the resume/job
    
            if (date.FoundDay) format += "-dd";//only print the day if it was actually found
        }
    
        return date.Date.format(DateTimeFormatter.ofPattern(format));
    }
}
```