package com.example.casemodule4group5.controller;

import com.example.casemodule4group5.model.entity.Category;
import com.example.casemodule4group5.service.category.ICategorySerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("/categories")

public class CategoryController {
    @Autowired
    private ICategorySerivce categorySerivce;

    @GetMapping
    public ResponseEntity<Page<Category>> findAll(@PageableDefault(5) Pageable pageable) {
        Page<Category> categories = categorySerivce.findAll(pageable);
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }
}
