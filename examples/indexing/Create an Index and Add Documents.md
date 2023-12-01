# Create an index and add a document

```java
import com.textkernel.tx.*;
import com.textkernel.tx.exceptions.TxException;
import com.textkernel.tx.models.matching.IndexType;
import com.textkernel.tx.models.resume.ParsedResume;

public class ParsingExample {
    public static void main(String[] args) {
        TxClient client = new TxClient("12345678", "abcdefghijklmnopqrstuvwxyz", DataCenter.US);
        
        ParsedResume parsedResume1 = ...;//output from Resume Parser
        ParsedResume parsedResume2 = ...;//output from Resume Parser
        
        String indexId = "myResumes";
    
        try {
            client.createIndex(IndexType.Resume, indexId);
            client.indexDocument(parsedResume1, indexId, "resume-1", null);
            client.indexDocument(parsedResume2, indexId, "resume-2", null);
            
            //if we get here, it was 200-OK and all operations succeeded
            System.out.println("Success!");
        }
        catch (TxException e) {
            //this was an outright failure, always try/catch for TxExceptions when using TxClient
            System.out.println("Error: " + e.TxErrorCode + ", Message: " + e.getMessage());
        }
    }
}
```