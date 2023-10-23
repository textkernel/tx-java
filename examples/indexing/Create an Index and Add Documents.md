# Create an index and add a document

```java
import com.textkernel.tx.*;
import com.textkernel.tx.exceptions.SovrenException;
import com.textkernel.tx.models.matching.IndexType;
import com.textkernel.tx.models.resume.ParsedResume;

public class ParsingExample {
    public static void main(String[] args) {
        SovrenClient client = new SovrenClient("12345678", "abcdefghijklmnopqrstuvwxyz", DataCenter.US);
        
        ParsedResume parsedResume1 = ...;//output from Sovren Resume Parser
        ParsedResume parsedResume2 = ...;//output from Sovren Resume Parser
        
        String indexId = "myResumes";
    
        try {
            client.createIndex(IndexType.Resume, indexId);
            client.indexDocument(parsedResume1, indexId, "resume-1", null);
            client.indexDocument(parsedResume2, indexId, "resume-2", null);
            
            //if we get here, it was 200-OK and all operations succeeded
            System.out.println("Success!");
        }
        catch (SovrenException e) {
            //this was an outright failure, always try/catch for SovrenExceptions when using SovrenClient
            System.out.println("Error: " + e.SovrenErrorCode + ", Message: " + e.getMessage());
        }
    }
}
```