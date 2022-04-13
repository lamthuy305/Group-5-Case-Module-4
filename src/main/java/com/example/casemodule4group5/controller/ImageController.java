package com.example.casemodule4group5.controller;

import com.example.casemodule4group5.model.dto.ImageForm;
import com.example.casemodule4group5.model.entity.Food;
import com.example.casemodule4group5.model.entity.Image;
import com.example.casemodule4group5.service.food.IFoodService;
import com.example.casemodule4group5.service.image.IImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/images")
public class ImageController {
    @Autowired
    private IImageService imageService;

    @Autowired
    private IFoodService foodService;

    @Value("${file-upload}")
    private String uploadPath;

    @GetMapping
    public ResponseEntity<Page<Image>> findAllImage(@RequestParam Optional<Long> id, @PageableDefault(40) Pageable pageable) {
        Page<Image> images = null; //xem lại
        if (id.isPresent()) {
            images = imageService.findImageByFoodId(id.get(), pageable);
        }
        return new ResponseEntity<>(images, HttpStatus.OK);
    }


    @PostMapping("/{id}")
    public ResponseEntity<List<Image>> save(@PathVariable Optional<Long> id, @ModelAttribute ImageForm imageForm) {
        Optional<Food> food = foodService.findById(id.get());
        if (!food.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<MultipartFile> images = imageForm.getImages();
        List<Image> imageList = new ArrayList<>();
        if (images.size() > 0) {
            for (MultipartFile image : images) {
                MultipartFile img = image;
                String fileName = img.getOriginalFilename();
                long currentTime = System.currentTimeMillis();
                fileName = currentTime + fileName;
                try {
                    FileCopyUtils.copy(image.getBytes(), new File(uploadPath + fileName));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Image imageFood = new Image(fileName, food.get());
                imageList.add(imageFood);
                imageService.save(imageFood);
            }
        }
        return new ResponseEntity<>(imageList, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Image> deleteImage(@PathVariable Long id) {
        Optional<Image> imageOptional = imageService.findById(id);
        if (!imageOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        imageService.removeById(id);
        return new ResponseEntity<>(imageOptional.get(), HttpStatus.OK);
    }
}
