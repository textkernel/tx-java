# Parsing with Geocoding and Indexing Example

```java
import com.sovren.*;
import com.sovren.exceptions.SovrenException;
import com.sovren.exceptions.SovrenUsableResumeException;
import com.sovren.models.Document;
import com.sovren.models.api.geocoding.GeocodeOptions;
import com.sovren.models.api.geocoding.GeocodeProvider;
import com.sovren.models.api.indexes.IndexSingleDocumentInfo;
import com.sovren.models.api.parsing.ParseOptions;
import com.sovren.models.api.parsing.ParseRequest;
import com.sovren.models.api.parsing.ParseResumeResponse;
import java.io.IOException;

public class ParsingExample {
    public static void main(String[] args) throws IOException {
        SovrenClient client = new SovrenClient("12345678", "abcdefghijklmnopqrstuvwxyz", DataCenter.US);
        
        //A Document is an unparsed File (PDF, Word Doc, etc)
        Document doc = new Document("resume.docx");
        
        ParseOptions options = new ParseOptions();
        options.GeocodeOptions = new GeocodeOptions();
        options.GeocodeOptions.IncludeGeocoding = true;

        options.IndexingOptions = new IndexSingleDocumentInfo();
        options.IndexingOptions.IndexId = "myResumes";
        options.IndexingOptions.DocumentId = "abc-123";

        //create a request to send
        ParseRequest request = new ParseRequest(doc, options);
    
        try {
            ParseResumeResponse response = client.parseResume(request);
            //if we get here, it was 200-OK and all operations succeeded
    
            System.out.println("Success!");
        }
        catch (SovrenUsableResumeException e) {
            //this indicates an error occurred when geocoding or indexing, but the
            //parsed resume may still be usable
        
            //do something with e.Response.Value.ResumeData if it has good data
        }
        catch (SovrenException e) {
            //this was an outright failure, always try/catch for SovrenExceptions when using SovrenClient
            System.out.println("Error: " + e.SovrenErrorCode + ", Message: " + e.getMessage());
        }
    }
}
```