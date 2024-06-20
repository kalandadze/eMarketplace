package com.example.eMarketplace.controller;

import com.example.eMarketplace.service.UserService;
import com.example.eMarketplace.util.JwtUtils;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.Date;
import java.util.Locale;

@RestController
@RequestMapping("/user")
public class UserController {
    UserService service;
    JwtUtils utils;

    @Autowired
    public UserController(UserService service, JwtUtils utils) {
        this.service = service;
        this.utils = utils;
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<?> addUser(@RequestParam("username") String username, @RequestParam("email") String email, @RequestParam("date") Long date, @RequestParam("password") String password) {
        try {
            service.addUser(username, email, new Date(date), password);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity<?> Login(@RequestParam("email") String email, @RequestParam("password") String password, HttpServletResponse response) {
        try {
            var token = service.login(email, password);
            response.addHeader(utils.JWT_HEADER, utils.JWT_PREFIX + token);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
