package edu.sprint.springbootsprint.exception.application;

public class ApplicationExceededLimitException extends RuntimeException {
    public ApplicationExceededLimitException(String message) {
        super(message);
    }
}
