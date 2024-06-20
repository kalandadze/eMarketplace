package com.example.eMarketplace.service.exeption;

public class IncorrectPasswordException extends RuntimeException {
    public IncorrectPasswordException() {
        super("incorrect password");
    }
}
