package com.istrategies.settings.exception;

public class ExistingCredentialsException extends BadRequestException{
    private static final String description = " - Existing Credentials Exception";

    public ExistingCredentialsException(String msg) {
        super(msg);
    }
}
