package org.pdf.custom_exceptions;

/**
 * The Exception is thrown when payload is incomplete
 * (e.g. either "pages" or "binary_template" object is missing)
 */
public class InvalidPayloadException extends RuntimeException {

    public InvalidPayloadException() {
        super();
    }

    public InvalidPayloadException(String message) {
        super(message);
    }
}
