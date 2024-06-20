package com.example.eMarketplace.service.exeption;

public class EmailAlreadyExistsException extends RuntimeException{
    public EmailAlreadyExistsException(String email) {
        super("email "+ email +" already exists");
    }
}
