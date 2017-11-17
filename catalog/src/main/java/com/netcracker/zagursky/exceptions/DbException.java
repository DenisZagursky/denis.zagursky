package com.netcracker.zagursky.exceptions;

/**
 * Created by Dzenyaa on 16.11.2017.
 */
public class DbException extends Exception {
    public DbException() {
    }

    public DbException(String message) {
        super(message);
    }

    public DbException(String message, Throwable cause) {
        super(message, cause);
    }

    public DbException(Throwable cause) {
        super(cause);
    }

}
