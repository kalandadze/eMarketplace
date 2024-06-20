package com.example.eMarketplace.util;

import com.example.eMarketplace.model.User;
import com.example.eMarketplace.repository.UserRepository;
import com.example.eMarketplace.service.exeption.EmailAlreadyExistsException;
import com.example.eMarketplace.service.exeption.EmailInvalidException;
import com.example.eMarketplace.service.exeption.UsernameInvalidException;
import jakarta.mail.internet.AddressException;
import jakarta.mail.internet.InternetAddress;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class UserValidator {

    private static final Pattern USER_USERNAME = Pattern.compile("^[a-zA-Z]{2,50}$");

    public boolean validateUserUsername(String username) {
        return USER_USERNAME.matcher(username).matches();
    }

    public boolean validateUserEmail(String email) {
        try {
            InternetAddress emailAddress = new InternetAddress(email);
            emailAddress.validate();
            return true;
        } catch (AddressException e) {
            return false;
        }
    }

    public void validateUser(User user,boolean isPresent) {
        if (!validateUserEmail(user.getEmail())) {
            throw new EmailInvalidException(user.getEmail());
        } else if (!validateUserUsername(user.getUsername())) {
            throw new UsernameInvalidException(user.getUsername());
        }
        if (isPresent) {
            throw new EmailAlreadyExistsException(user.getEmail());
        }
    }
}
