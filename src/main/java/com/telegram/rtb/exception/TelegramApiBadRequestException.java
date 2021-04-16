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

    /**
     * TelegramApiBadRequestException constructor.
     *
     * @param message     the errorMessage
     * @param apiResponse the api response
     * @param errorCode   the error code
     */
    public TelegramApiBadRequestException(String message, String apiResponse, Integer errorCode) {
        super(message);
        this.apiResponse = apiResponse;
        this.errorCode = errorCode;
    }

}
