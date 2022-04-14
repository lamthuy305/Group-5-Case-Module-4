package com.example.casemodule4group5.controller;

import com.example.casemodule4group5.model.dto.CategoryForm;
import com.example.casemodule4group5.model.entity.Category;
import com.example.casemodule4group5.repostory.IFoodCount;
import com.example.casemodule4group5.repostory.IFoodRepository;
import com.example.casemodule4group5.service.category.ICategorySerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/categories")

public class CategoryController {
    @Autowired
    private ICategorySerivce categorySerivce;
    @Value("${file-upload}")
    private String uploadPath;

    @GetMapping
    public ResponseEntity<Iterable<Category>> findAll() {
        Iterable<Category> categories = categorySerivce.findAll();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> findById(@PathVariable Long id) {
        Optional<Category> categoryOptional = categorySerivce.findById(id);
        if (!categoryOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(categoryOptional.get(), HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<Category> save(@ModelAttribute CategoryForm categoryForm) {
        MultipartFile image = categoryForm.getImage();
        String fileName = image.getOriginalFilename();
        long currentTime = System.currentTimeMillis();
        fileName = currentTime+fileName;
        try {
            FileCopyUtils.copy(image.getBytes(), new File(uploadPath + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Category newCategory = new Category(fileName, categoryForm.getName());
        categorySerivce.save(newCategory);
        return new ResponseEntity<>(categorySerivce.save(newCategory), HttpStatus.CREATED);
    }

    @PostMapping("/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable Long id, @ModelAttribute CategoryForm categoryForm) {
        Optional<Category> categoryOptional = categorySerivce.findById(id);
        if (!categoryOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        MultipartFile image = categoryForm.getImage();
        String fileName = image.getOriginalFilename();
        long currentTime = System.currentTimeMillis();
        fileName = currentTime+fileName;
        try {
            FileCopyUtils.copy(image.getBytes(), new File(uploadPath + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Category category = categoryOptional.get();
        category.setImage(fileName);
        category.setName(categoryForm.getName());
        categorySerivce.save(category);
        return new ResponseEntity<>(categorySerivce.save(category), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Category> deleteCategory(@PathVariable Long id) {
        Optional<Category> categoryOptional = categorySerivce.findById(id);
        if (!categoryOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        categorySerivce.removeById(id);
        return new ResponseEntity<>(categoryOptional.get(), HttpStatus.OK);
    }


    @GetMapping("/foodCount")
    public ResponseEntity<List<IFoodCount>> foodCount() {
        List<IFoodCount> foodCountList = categorySerivce.countTotalFoodOfCategoryInterface();
        return new ResponseEntity<>(foodCountList, HttpStatus.OK);
    }


}
