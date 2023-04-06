package edu.sprint.springbootsprint.exception.application;

public class ApplicationDismissedEmployeeException extends RuntimeException {
    public ApplicationDismissedEmployeeException(String message) {
        super(message);
    }
}
