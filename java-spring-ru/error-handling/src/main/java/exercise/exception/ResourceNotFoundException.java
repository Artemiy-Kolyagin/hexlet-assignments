package exercise.exception;

import javax.xml.transform.sax.SAXResult;

// BEGIN
public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String message){
        super(message);
    }
}
// END
