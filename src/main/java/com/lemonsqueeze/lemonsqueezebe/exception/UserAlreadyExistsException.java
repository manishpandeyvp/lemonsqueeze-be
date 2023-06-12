package com.lemonsqueeze.lemonsqueezebe.exception;

public class UserAlreadyExistsException extends RuntimeException{
    public UserAlreadyExistsException(String username) {
        super(username + " already exists, please select some other username");
    }
}
