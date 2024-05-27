package com.example.eMarketplace.repository;

import com.example.eMarketplace.model.Listing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ListingRepository extends JpaRepository<Listing,Integer> {
    Optional<Listing> findByName(String name);
}
