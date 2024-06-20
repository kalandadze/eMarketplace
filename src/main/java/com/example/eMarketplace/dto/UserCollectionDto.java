package com.example.eMarketplace.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class UserCollectionDto {
    List<UserDto> usersList;
}
