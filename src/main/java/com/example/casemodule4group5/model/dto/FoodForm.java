package com.example.casemodule4group5.model.dto;

import com.example.casemodule4group5.model.entity.Category;
import com.example.casemodule4group5.model.entity.Tag;
import com.example.casemodule4group5.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;


@Data
public class FoodForm {
    private Long id;

    private String name;

    private MultipartFile img;

    private String description;

    private double price;

    private double salePrice;

    private double serviceFee;

    private Date dayCreate;

    private Date dayChange;

    private Tag tag;

    private List<MultipartFile> images;

    private User user;

    private Category category;
}
