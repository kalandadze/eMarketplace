package com.example.eMarketplace.service;

import com.example.eMarketplace.dto.ListingCollectionDto;
import com.example.eMarketplace.dto.ListingDto;
import com.example.eMarketplace.model.Listing;
import com.example.eMarketplace.repository.ListingRepository;
import com.example.eMarketplace.repository.UserRepository;
import com.example.eMarketplace.service.exeption.EntityNotFoundException;
import com.example.eMarketplace.util.ModelConverter;
import jakarta.annotation.PostConstruct;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Repository
public class MarketService {
    ListingRepository repository;
    UserRepository userRepository;
    ModelConverter converter;

    private final Path root = Paths.get("photos");

    @Autowired
    public MarketService(ListingRepository repository, UserRepository userRepository, ModelConverter converter) {
        this.repository = repository;
        this.userRepository = userRepository;
        this.converter = converter;
    }

    @PostConstruct
    private void init() {
        try {
            Files.createDirectories(root);
        } catch (IOException e) {
            throw new RuntimeException("Could not create or find root folder");
        }
    }

    public ListingCollectionDto getListingsOnPage(int page) {
        Pageable pageable = PageRequest.of(page, 6);
        val listings = repository.findAll(pageable).stream().toList();
        return converter.covert(listings, repository.findAll().size());
    }

    public void addListing(Listing listing, MultipartFile file, String email) {
        try {
            String url = listing.getName() + "-photo.jpg";
            Files.copy(file.getInputStream(), root.resolve(url), StandardCopyOption.REPLACE_EXISTING);
            listing.setPhotoUrl(url);
            listing.setUser(userRepository.findByEmail(email).orElseThrow(() -> new EntityNotFoundException("user with email " + email + " does not exist")));
            repository.save(listing);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public ListingDto getListingByName(String name) {
        Listing listing = repository.findByName(name).orElseThrow(() -> new EntityNotFoundException("Listing with the name " + name + " not found"));
        return converter.convert(listing);
    }

    public ListingCollectionDto getListingsOnPageByPriceAscending(int page) {
        Pageable pageable = PageRequest.of(page, 6, Sort.by("price").ascending());
        val listings = repository.findAll(pageable).stream().toList();
        return converter.covert(listings,repository.findAll().size());
    }

    public ListingCollectionDto getListingsOnPageByPriceDescending(int page) {
        Pageable pageable = PageRequest.of(page, 6, Sort.by("price").descending());
        val listings = repository.findAll(pageable).stream().toList();
        return converter.covert(listings, repository.findAll().size());
    }

    public ListingCollectionDto getListingsOnPageByDateAscending(int page) {
        Pageable pageable = PageRequest.of(page, 6, Sort.by("submissionTime").ascending());
        val listings = repository.findAll(pageable).stream().toList();
        return converter.covert(listings, repository.findAll().size());
    }

    public ListingCollectionDto getListingsOnPageByDateDescending(int page) {
        Pageable pageable = PageRequest.of(page, 6, Sort.by("submissionTime").descending());
        val listings = repository.findAll(pageable).stream().toList();
        return converter.covert(listings, repository.findAll().size());
    }
}
