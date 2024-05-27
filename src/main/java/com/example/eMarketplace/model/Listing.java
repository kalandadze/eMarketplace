package com.example.eMarketplace.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "listing")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Listing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private double price;
    private String description;
    @Column(name = "submission_time")
    private Date submissionTime;
    @Column(name = "photo_url")
    private String photoUrl;
}
