package com.reliaquest.api.exception;

/**
 * Custom exceptions for the Employee API.
 */
public class ApiExceptions {

    /**
     * Thrown when an employee is not found.
     */
    public static class EmployeeNotFoundException extends RuntimeException {
        public EmployeeNotFoundException(String id) {
            super("Employee not found with id: " + id);
        }
    }

    /**
     * Thrown when there is an error communicating with the external/mock server.
     */
    public static class ExternalServiceException extends RuntimeException {
        public ExternalServiceException(String message) {
            super(message);
        }
    }
}