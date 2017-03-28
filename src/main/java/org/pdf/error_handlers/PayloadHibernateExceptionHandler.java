package org.pdf.error_handlers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

//@RestControllerAdvice
public class PayloadHibernateExceptionHandler extends AbstractExceptionHandler{
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> processValidationError(MethodArgumentNotValidException ex, HttpServletRequest req) {
//        BindingResult result = ex.getBindingResult();
        return super.processError(ex, req);
    }
}