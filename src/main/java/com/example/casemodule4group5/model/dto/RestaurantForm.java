package com.example.casemodule4group5.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantForm {
    private Long id;

    private String name;

    private MultipartFile img;

    private String address;

    private String openTime;

    private String closeTime;
}
