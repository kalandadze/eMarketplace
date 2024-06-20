package com.example.eMarketplace.util;

import com.example.eMarketplace.dto.ListingCollectionDto;
import com.example.eMarketplace.dto.ListingDto;
import com.example.eMarketplace.dto.UserDto;
import com.example.eMarketplace.model.Listing;
import com.example.eMarketplace.model.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ModelConverter {
    public ListingCollectionDto covert(List<Listing> listings) {
        return ListingCollectionDto.builder()
                .listingCollection(listings.stream().map(this::convert).toList())
                .build();
    }
    public ListingDto convert(Listing listing){
        return ListingDto.builder()
                .name(listing.getName())
                .price(listing.getPrice())
                .description(listing.getDescription())
                .submissionTime(listing.getSubmissionTime())
                .photoUrl(listing.getPhotoUrl())
                .user(convert(listing.getUser()))
                .build();
    }
    public UserDto convert(User user){
        return UserDto.builder()
                .email(user.getEmail())
                .birthdate(user.getBirthdate())
                .username(user.getUsername())
                .build();
    }
}
