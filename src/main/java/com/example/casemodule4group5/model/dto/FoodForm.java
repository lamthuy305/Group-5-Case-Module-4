package com.example.casemodule4group5.model.dto;

import com.example.casemodule4group5.model.entity.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class FoodForm {
    private Long id;

    private String foodName;

    private MultipartFile img;

    private double price;

    private double salePrice;

    private double serviceFee;

    private String dayCreate;

    private String dayChange;

    private Tag tag;
}
