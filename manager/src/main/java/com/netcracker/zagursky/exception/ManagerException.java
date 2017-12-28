package com.netcracker.zagursky.exception;

/**
 * Created by Dzenyaa on 08.12.2017.
 */
public class ManagerException extends Exception {
    public ManagerException() {
    }

    public ManagerException(String message) {
        super(message);
    }

    public ManagerException(String message, Throwable cause) {
        super(message, cause);
    }

    public ManagerException(Throwable cause) {
        super(cause);
    }
}
