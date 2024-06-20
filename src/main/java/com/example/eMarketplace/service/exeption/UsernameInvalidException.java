package com.example.eMarketplace.service.exeption;

public class UsernameInvalidException extends RuntimeException{
    public UsernameInvalidException(String username) {
        super(username+" does not match username requirements");
    }
}
