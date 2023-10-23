# Parsing with Geocoding and Indexing Example

```java
import com.textkernel.tx.*;
import com.textkernel.tx.exceptions.SovrenException;
import com.textkernel.tx.exceptions.SovrenUsableResumeException;
import com.textkernel.tx.models.Document;
import com.textkernel.tx.models.api.geocoding.GeocodeOptions;
import com.textkernel.tx.models.api.geocoding.GeocodeProvider;
import com.textkernel.tx.models.api.indexes.IndexSingleDocumentInfo;
import com.textkernel.tx.models.api.parsing.ParseOptions;
import com.textkernel.tx.models.api.parsing.ParseRequest;
import com.textkernel.tx.models.api.parsing.ParseResumeResponse;
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