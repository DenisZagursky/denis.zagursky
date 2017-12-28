package com.netcracker.zagursky.exceptions;

/**
 * Created by Dzenyaa on 16.11.2017.
 */
public class CatalogException extends Exception {
    public CatalogException() {
    }

    public CatalogException(String message) {
        super(message);
    }

    public CatalogException(String message, Throwable cause) {
        super(message, cause);
    }

    public CatalogException(Throwable cause) {
        super(cause);
    }

}
