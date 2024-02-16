package com.system32.systemapi.api.exceptions;

public class CommandNotRegistered extends RuntimeException {
    public CommandNotRegistered(String message) {
        super(message);
    }
}
