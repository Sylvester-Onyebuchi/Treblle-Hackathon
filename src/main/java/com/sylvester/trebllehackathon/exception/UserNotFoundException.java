package com.sylvester.trebllehackathon.exception;



public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String userNotFound) {
        super(userNotFound);
    }
}
