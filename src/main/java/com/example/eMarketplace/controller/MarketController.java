package com.example.eMarketplace.controller;

import com.example.eMarketplace.model.Listing;
import com.example.eMarketplace.service.MarketService;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@RestController
@RequestMapping("/market")
public class MarketController {
    MarketService service;

    @Autowired
    public MarketController(MarketService service) {
        this.service = service;
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity<?> getAllListingsOnPage(@RequestParam("page") int page) {
        try {
            val response = service.getListingsOnPage(page);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/{name}")
    @ResponseBody
    public ResponseEntity<?> getListingByName(@PathVariable("name") String name) {
        try {
            val response = service.getListingByName(name);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<?> addListing(@RequestParam("photo") MultipartFile photo, @RequestParam("name") String name, @RequestParam("price") Double price, @RequestParam("description") String description) {
        try {
            service.addListing(Listing.builder()
                    .description(description)
                    .submissionTime(new Date())
                    .name(name)
                    .price(price)
                    .build(), photo);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
