package com.lemonsqueeze.lemonsqueezebe.exception;

public class FollowingItselfException extends RuntimeException {
    public FollowingItselfException() {
        super("Following itself is not allowed!");
    }
}
