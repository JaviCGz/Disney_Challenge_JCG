package com.alkemy.disney.disney.exception;

public class InvalidDTOException extends RuntimeException {
    
    /**
     * Exception thrown when receiving a DTO with missing properties
     * @param message: Describes the exception
     */
    public InvalidDTOException(String message) {
        super(message);
    }
}
