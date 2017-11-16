package com.netcracker.zagursky.exceptions;

/**
 * Created by Dzenyaa on 16.11.2017.
 */
public class BdException extends Exception {
    public BdException() {
    }
    public BdException(String message) {
        super(message);
    }
    public BdException(String message, Throwable cause)
    {
        super(message,cause);
    }
    public BdException(Throwable cause)
    {
        super(cause);
    }

}
