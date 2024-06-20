package com.example.eMarketplace.service;

import com.example.eMarketplace.dto.UserDto;
import com.example.eMarketplace.model.User;
import com.example.eMarketplace.repository.UserRepository;
import com.example.eMarketplace.service.exeption.*;
import com.example.eMarketplace.util.JwtUtils;
import com.example.eMarketplace.util.ModelConverter;
import com.example.eMarketplace.util.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;

@Service
public class UserService {
    UserRepository repository;
    ModelConverter converter;
    UserValidator validator;
    JwtUtils jwtUtils;
    BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository repository, ModelConverter converter, UserValidator validator, JwtUtils jwtUtils, BCryptPasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.converter = converter;
        this.validator = validator;
        this.jwtUtils = jwtUtils;
        this.passwordEncoder = passwordEncoder;
    }


    public User getUserByEmail(String email) {
        return repository.findByEmail(email).orElseThrow(() -> new EntityNotFoundException("user with email - " + email + " does not exist"));
    }

    public void addUser(String username, String email, Date date, String password) {
        User user = User.builder()
                .birthdate(date)
                .email(email)
                .username(username)
                .password(passwordEncoder.encode(password)).build();
        validator.validateUser(user, repository.findByEmail(email).isPresent());
        repository.save(user);
    }

    public String login(String email, String password) {
        User user = getUserByEmail(email);
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new IncorrectPasswordException();
        }
        return jwtUtils.generateToken(email);
    }
}
