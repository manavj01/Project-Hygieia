package com.hygieia.Project.Hygieia.exceptionHandling;

public class UserResponseBuildException extends RuntimeException {
    public UserResponseBuildException(String message, Exception e) {
        super(message, e);
    }
}
