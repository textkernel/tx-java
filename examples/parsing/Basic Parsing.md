# Basic Resume Parsing Example

```java
import com.sovren.*;
import com.sovren.exceptions.SovrenException;
import com.sovren.models.Document;
import com.sovren.models.SovrenDate;
import com.sovren.models.api.parsing.ParseOptions;
import com.sovren.models.api.parsing.ParseRequest;
import com.sovren.models.api.parsing.ParseResumeResponse;
import com.sovren.models.resume.PersonalAttributes;
import com.sovren.models.resume.contactinfo.ContactInformation;
import com.sovren.models.resume.contactinfo.WebAddress;
import com.sovren.models.resume.contactinfo.WebAddressType;
import com.sovren.models.resume.education.EducationDetails;
import com.sovren.models.resume.education.EducationHistory;
import com.sovren.models.resume.employment.EmploymentHistory;
import com.sovren.models.resume.employment.Position;
import java.io.IOException;
import java.time.format.DateTimeFormatter;

public class ParsingExample {
    public static void main(String[] args) throws IOException {
        SovrenClient client = new SovrenClient("12345678", "abcdefghijklmnopqrstuvwxyz", DataCenter.US, null);
        
        //A Document is an unparsed File (PDF, Word Doc, etc)
        Document doc = new Document("resume.docx");
    
        //when you create a ParseRequest, you can specify many configuration settings
        //in the ParseOptions. See https://docs.sovren.com/API/Rest/Parsing#parse-resume
        ParseRequest request = new ParseRequest(doc, new ParseOptions());
    
        try {
            ParseResumeResponse response = client.parseResume(request);
            //if we get here, it was 200-OK and all operations succeeded
    
            //now we can use the response from Sovren to output some of the data from the resume
            printBasicResumeInfo(response);
        }
        catch (SovrenException e) {
            //this was an outright failure, always try/catch for SovrenExceptions when using
            // the ParsingService
            System.out.println("Error: " + e.SovrenErrorCode + ", Message: " + e.getMessage());
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
                System.out.println(endDateRepresents + ": " + getSovrenDateAsString(edu.LastEducationDate));
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