package com.example.casemodule4group5.controller;

import com.example.casemodule4group5.model.dto.RestaurantForm;
import com.example.casemodule4group5.model.entity.Restaurant;
import com.example.casemodule4group5.service.restaurant.IRestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/restaurants")
public class RestaurantController {
    @Autowired
    IRestaurantService restaurantService;

    @Value("${file-upload}")
    private String uploadPath;

    @GetMapping
    public ResponseEntity<Page<Restaurant>> findAll(@RequestParam(name = "q") Optional<String> q, @PageableDefault Pageable pageable) {
        Page<Restaurant> restaurants;
        if (!q.isPresent()) {
            restaurants = restaurantService.findAll(pageable);
        } else {
            restaurants = restaurantService.findAllByName(q.get(), pageable);
        }
        if (restaurants.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(restaurants, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Restaurant> findById(@PathVariable Long id) {
        Optional<Restaurant> restaurantOptional = restaurantService.findById(id);
        if (!restaurantOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(restaurantOptional.get(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Restaurant> save(@ModelAttribute RestaurantForm restaurantForm) {
        MultipartFile img = restaurantForm.getImg();
        String fileName = img.getOriginalFilename();
        long currentTime = System.currentTimeMillis();
        fileName = currentTime + fileName;
        try {
            FileCopyUtils.copy(img.getBytes(), new File(uploadPath + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Restaurant restaurant = new Restaurant(restaurantForm.getId(), restaurantForm.getName(), fileName, restaurantForm.getAddress(), restaurantForm.getOpenTime(), restaurantForm.getCloseTime());
        restaurantService.save(restaurant);
        return new ResponseEntity<>(restaurant, HttpStatus.CREATED);
    }

    @PostMapping("/{id}")
    public ResponseEntity<Restaurant> updateRestaurant(@PathVariable Long id, @ModelAttribute RestaurantForm restaurantForm) {
        Optional<Restaurant> restaurantOptional = restaurantService.findById(id);
        if (!restaurantOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        MultipartFile img = restaurantForm.getImg();
        String fileName = "";
        if (img != null) {
            fileName = img.getOriginalFilename();
            long currentTimeMillis = System.currentTimeMillis();
            fileName = currentTimeMillis + fileName;
            try {
                FileCopyUtils.copy(restaurantForm.getImg().getBytes(), new File(uploadPath + fileName));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            fileName = restaurantOptional.get().getImg();
        }
        Restaurant restaurant = new Restaurant(restaurantForm.getId(), restaurantForm.getName(), fileName, restaurantForm.getAddress(), restaurantForm.getOpenTime(), restaurantForm.getCloseTime());
        return new ResponseEntity<>(restaurantService.save(restaurant), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Restaurant> deleteRestaurant(@PathVariable Long id) {
        Optional<Restaurant> restaurantOptional = restaurantService.findById(id);
        if (!restaurantOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        restaurantService.removeById(id);
        return new ResponseEntity<>(restaurantOptional.get(), HttpStatus.OK);
    }
}
