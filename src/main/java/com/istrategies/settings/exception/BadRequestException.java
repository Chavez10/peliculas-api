package com.istrategies.settings.exception;

public class BadRequestException extends RuntimeException{
    private static final String description = "Bad Request Exception (404)";

    public BadRequestException(String msg){
        super(description+". "+msg);
    }
}
