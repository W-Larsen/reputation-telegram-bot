package com.telegram.drb.exception;

/**
 * Bad request exception.
 *
 * @author Valentyn Korniienko
 */
public class BadRequestException extends RuntimeException {

    public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}
