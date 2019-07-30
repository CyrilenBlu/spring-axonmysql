package blu.axonmysqlclient.controllers.advice;

import blu.axonmysqlclient.exceptions.ElementNotFoundException;
import blu.axonmysqlclient.exceptions.InvalidJsonPropertyValueException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ElementNotFoundException.class)
    public ResponseEntity<Object> handleNotFoundException(Exception exception, WebRequest request) {
        return new ResponseEntity<>(exception.getMessage() == null? "Resource not found!" : exception.getMessage(),
                new HttpHeaders(),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidJsonPropertyValueException.class)
    public ResponseEntity<Object> handleInvalidJsonPropertyValueException(Exception e, WebRequest request) {
        return new ResponseEntity<>(e.getMessage(), new HttpHeaders(), HttpStatus.NOT_ACCEPTABLE);
    }
}
