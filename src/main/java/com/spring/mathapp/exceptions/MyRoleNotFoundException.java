package com.spring.mathapp.exceptions;

public class MyRoleNotFoundException extends RuntimeException {

    public MyRoleNotFoundException(String message){
        super(message);
    }
}
