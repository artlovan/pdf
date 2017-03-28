package org.pdf.error_handlers;

import org.pdf.custom_exceptions.InvalidPayloadException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;


/**
 * Class handles InvalidPayloadException Exception.
 */
@RestControllerAdvice
public class InvalidPayloadExceptionHandler extends AbstractExceptionHandler{

    /**
     * Response body is created by adding information into response body.
     * @param ex
     * @return
     */
    @ExceptionHandler(InvalidPayloadException.class)
    public ResponseEntity<?> processError(InvalidPayloadException ex, HttpServletRequest req) {
        return super.processError(ex, req);
    }
}
