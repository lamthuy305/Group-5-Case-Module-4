package com.example.casemodule4group5.model.dto;

import com.example.casemodule4group5.model.entity.Image;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImageForm {
    private List<MultipartFile> images;
}
