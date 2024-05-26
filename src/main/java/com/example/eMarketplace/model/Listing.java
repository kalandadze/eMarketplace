package com.example.eMarketplace.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "listing")
@Data
public class Listing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private double price;
    private String description;
    private Date submissionTime;
    @Column(name = "photo_url")
    private String photoUrl;
}
