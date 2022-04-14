package com.example.casemodule4group5.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryForm {
    private Long id;
    private MultipartFile image;
    private String name;
}
