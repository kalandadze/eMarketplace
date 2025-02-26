package com.example.eMarketplace.controller;

import com.example.eMarketplace.model.Listing;
import com.example.eMarketplace.service.MarketService;
import com.example.eMarketplace.util.JwtUtils;
import jakarta.servlet.http.HttpServletResponse;
import lombok.val;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@RestController
@RequestMapping("/market")
public class MarketController {
    private final MarketService service;
    private final JwtUtils jwtUtils;

    public MarketController(MarketService service, JwtUtils utils) {
        this.service = service;
        this.jwtUtils = utils;
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

    @GetMapping("/DateDesc")
    @ResponseBody
    public ResponseEntity<?> getAllListingsOnPageByDateDescending(@RequestParam("page") int page) {
        try {
            val response = service.getListingsOnPageByDateDescending(page);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/DateAsc")
    @ResponseBody
    public ResponseEntity<?> getAllListingsOnPageByDateAscending(@RequestParam("page") int page) {
        try {
            val response = service.getListingsOnPageByDateAscending(page);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/PriceDesc")
    @ResponseBody
    public ResponseEntity<?> getAllListingsOnPageByPriceDescending(@RequestParam("page") int page) {
        try {
            val response = service.getListingsOnPageByPriceDescending(page);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/PriceAsc")
    @ResponseBody
    public ResponseEntity<?> getAllListingsOnPageByPriceAscending(@RequestParam("page") int page) {
        try {
            val response = service.getListingsOnPageByPriceAscending(page);
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
    public ResponseEntity<?> addListing(@RequestHeader("Authorization") String token, @RequestParam("photo") MultipartFile photo, @RequestParam("name") String name, @RequestParam("price") Double price, @RequestParam("description") String description, HttpServletResponse response) {
        try {
            String email = jwtUtils.getEmailFromToken(token.replace(jwtUtils.JWT_PREFIX, ""));
            service.addListing(Listing.builder().description(description)
                            .submissionTime(new Date())
                            .name(name)
                            .price(price)
                            .build(),
                    photo, email);
            response.sendRedirect("http://"+System.getenv("IP_ADDR")+":8080");
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
