package com.example.casemodule4group5.controller;

import com.example.casemodule4group5.model.entity.Cart;
import com.example.casemodule4group5.model.entity.Category;
import com.example.casemodule4group5.service.category.ICategorySerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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

    @GetMapping("/{id}")
    public ResponseEntity<Category> findById(@PathVariable Long id) {
        Optional<Category> categoryOptional = categorySerivce.findById(id);
        if (!categoryOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(categoryOptional.get(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> updateOrder(@PathVariable Long id, @RequestBody Category category) {
        Optional<Category> categoryOptional = categorySerivce.findById(id);
        if (!categoryOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        category.setId(id);
        return new ResponseEntity<>(categorySerivce.save(category), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Category> deleteOrder(@PathVariable Long id) {
        Optional<Category> categoryOptional = categorySerivce.findById(id);
        if (!categoryOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        categorySerivce.removeById(id);
        return new ResponseEntity<>(categoryOptional.get(), HttpStatus.OK);
    }
}
