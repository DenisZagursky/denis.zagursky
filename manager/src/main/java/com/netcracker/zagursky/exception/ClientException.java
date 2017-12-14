package com.netcracker.zagursky.exception;

/**
 * Created by Dzenyaa on 08.12.2017.
 */
public class ClientException extends Exception {
    public ClientException() {
    }

    public ClientException(String message) {
        super(message);
    }

    public ClientException(String message, Throwable cause) {
        super(message, cause);
    }

    public ClientException(Throwable cause) {
        super(cause);
    }
}
