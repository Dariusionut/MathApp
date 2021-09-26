package com.spring.mathapp.exceptions;

public class CountryNotFoundException extends RuntimeException {

    public CountryNotFoundException() {
        super();
    }

    public CountryNotFoundException(String message) {
        super(message);
    }

    public CountryNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public CountryNotFoundException(Throwable cause) {
        super(cause);
    }
}
