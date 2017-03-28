package org.pdf.error_handlers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * {
     "timestamp": 1485783866313,
     "status": 500,
     "error": "Internal Server Error",
     "exception": "java.lang.IllegalArgumentException",
     "message": "Input byte array has wrong 4-byte ending unit",
     "path": "/api/pdf/parser"
     }
 */
@RestControllerAdvice
public class IllegalArgumentExceptionHandler extends AbstractExceptionHandler{

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> processError(IllegalArgumentException ex, HttpServletRequest req) {
        return super.processError(ex, req);
    }
}
