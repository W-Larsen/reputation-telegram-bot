package com.telegram.rtb.exception;

import lombok.Getter;

/**
 * Telegram api bad request exception.
 *
 * @author Valentyn Korniienko
 */
@Getter
public class TelegramApiBadRequestException extends RuntimeException {

    private String apiResponse;
    private Integer errorCode;

    public TelegramApiBadRequestException(String message) {
        super(message);
    }

    public TelegramApiBadRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    public TelegramApiBadRequestException(String message, String apiResponse, Integer errorCode) {
        super(message);
        this.apiResponse = apiResponse;
        this.errorCode = errorCode;
    }

}
