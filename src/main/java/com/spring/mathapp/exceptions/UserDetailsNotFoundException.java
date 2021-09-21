package com.spring.mathapp.exceptions;

public class UserDetailsNotFoundException extends RuntimeException {

    public UserDetailsNotFoundException() {
    }

    public UserDetailsNotFoundException(String message){
        super(message);
    }
}
