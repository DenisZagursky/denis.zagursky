package com.netcracker.zagursky.exceptions;

/**
 * Created by Dzenyaa on 16.11.2017.
 */
public class InventoryException extends Exception {
    public InventoryException() {
    }

    public InventoryException(String message) {
        super(message);
    }

    public InventoryException(String message, Throwable cause) {
        super(message, cause);
    }

    public InventoryException(Throwable cause) {
        super(cause);
    }

}
