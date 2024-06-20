package com.example.eMarketplace.service.exeption;

public class EmailInvalidException extends RuntimeException{
    public EmailInvalidException(String email) {
        super("email - "+email+" does not exist");
    }
}
