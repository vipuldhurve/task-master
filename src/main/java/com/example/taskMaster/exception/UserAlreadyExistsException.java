package com.example.taskMaster.exception;

public class UserAlreadyExistsException extends RuntimeException {
    private String resourceName;
    private String fieldName;
    private String fieldValue;

    public UserAlreadyExistsException(String resourceName, String fieldName, String fieldValue) {
        // User with username: user123 already exists!
        super(String.format("%s with %s : '%s' already exists!", resourceName, fieldName, fieldValue));
    }

    public UserAlreadyExistsException(String username) {
        // User with username: user123 already exists!
        this("User", "username", username);
    }

    public UserAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
