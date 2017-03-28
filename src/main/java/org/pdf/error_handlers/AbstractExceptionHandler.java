package org.pdf.error_handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;
import java.util.HashMap;

public abstract class AbstractExceptionHandler {

    public ResponseEntity<?> processError(Exception ex, HttpServletRequest req) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("timestamp", Instant.now().toEpochMilli());
        map.put("status", HttpStatus.BAD_REQUEST.value());
        map.put("error", HttpStatus.BAD_REQUEST.getReasonPhrase());
        map.put("message", ex.getMessage());
        map.put("exception", ex.getClass());
        map.put("path", req.getServletPath());
        return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
    }
}
