package com.alkemy.disney.disney.exception;

public class NotFoundException extends RuntimeException{
    /**
     * Exception thrown when a database search returns nothing.
     * @param error: Describes the exception
     */
    public NotFoundException(String error) {
    super(error);
    }
    
}
