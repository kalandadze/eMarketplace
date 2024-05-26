package com.example.eMarketplace.controller;

import com.example.eMarketplace.service.MarketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/market")
public class MarketController {
    MarketService service;

    @Autowired
    public MarketController(MarketService service) {
        this.service = service;
    }
}
