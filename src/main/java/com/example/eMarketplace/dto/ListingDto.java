package com.example.eMarketplace.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class ListingDto {
    private String name;
    private double price;
    private String description;
    private Date submissionTime;
    private String photoUrl;
}
