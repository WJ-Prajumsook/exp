package org.wj.prajumsook.exception;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Log4j2
@ControllerAdvice
@RestControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<CustomerApiException> handleApiException(BookNotFoundException ex) {
        logException(ex);
        ApiException exception = new ApiException();
        exception.setCode("error-000404");
        exception.setType(HttpStatus.NOT_FOUND.toString());
        exception.setMessage("No book found with id: " + ex.getId());
        CustomerApiException customerApiException = new CustomerApiException(exception);

        return new ResponseEntity<>(customerApiException, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<CustomerApiException> handleException(Exception ex) {
        logException(ex);
        ApiException apiException = new ApiException();
        apiException.setCode("error-000500");
        apiException.setType(HttpStatus.INTERNAL_SERVER_ERROR.toString());
        apiException.setMessage(ex.getMessage());

        return new ResponseEntity<>(new CustomerApiException(apiException), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private void logException(Throwable t) {
        StringBuilder sb = new StringBuilder();
        for(StackTraceElement element : t.getStackTrace()) {
            sb.append(element.toString());
            sb.append("\n");
        }

        log.error("ERROR: [\n" + sb.toString() + "]");
    }
}
