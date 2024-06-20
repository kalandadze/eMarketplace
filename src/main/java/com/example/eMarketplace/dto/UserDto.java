package com.example.eMarketplace.dto;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.Date;

@Data
@Builder
public class UserDto {
    private String username;
    private String email;
    private Date birthdate;
}
