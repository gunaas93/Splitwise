package com.company.exception;

/**
 * Created by manishsharma on 08/01/18.
 */
public class SplitwiseException extends RuntimeException {

    public SplitwiseException(String message) {
        super(message);
    }

    public SplitwiseException(String message, Throwable cause) {
        super(message, cause);
    }
}
