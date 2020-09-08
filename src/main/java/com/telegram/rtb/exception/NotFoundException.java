package com.telegram.rtb.exception;

/**
 * Not found exception.
 *
 * @author Valentyn Korniienko
 */
public class NotFoundException extends RuntimeException {

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
