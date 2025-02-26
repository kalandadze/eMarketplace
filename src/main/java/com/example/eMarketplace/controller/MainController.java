package com.example.eMarketplace.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/")
public class MainController {

    @GetMapping
    public ModelAndView homePage() {
        return new ModelAndView("redirect:http://" + System.getenv("IP_ADDR") + ":8080/main.html");
    }


}