package ru.agiletech.teammate.service.infrastructure.persistence;

public class TeammateNotFoundException extends RuntimeException{

    public TeammateNotFoundException() {
    }

    public TeammateNotFoundException(String message) {
        super(message);
    }

    public TeammateNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public TeammateNotFoundException(Throwable cause) {
        super(cause);
    }

    public TeammateNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
