package calvin.inventarios.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFindedException extends RuntimeException{
    public ResourceNotFindedException(String message){
        super(message);
    }
}
