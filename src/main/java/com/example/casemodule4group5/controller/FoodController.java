package com.example.casemodule4group5.controller;

import com.example.casemodule4group5.model.dto.FoodForm;
import com.example.casemodule4group5.model.entity.Category;
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
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/foods")
public class FoodController {
    @Autowired
    private IFoodService foodService;

    @Autowired
    private IImageService imageService;

    @Value("${file-upload}")
    private String uploadPath;

    @GetMapping
    public ResponseEntity<Page<Food>> findAll(@RequestParam(name = "q") Optional<String> q, @RequestParam(name = "slug") Optional<String> slug, @PageableDefault(5) Pageable pageable) {
        Page<Food> foods = foodService.findAll(pageable);
        if (q.isPresent()) {
            foods = foodService.findFoodByNameContaining(q.get(), pageable);
        }
        if (slug.isPresent()) {
            foods = foodService.findAllFoodByTag(slug.get(), pageable);
        }
        return new ResponseEntity<>(foods, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Food> findById(@PathVariable Long id) {
        Optional<Food> productOptional = foodService.findById(id);
        if (!productOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(productOptional.get(), HttpStatus.OK);
    }



    @PostMapping
    public ResponseEntity<Food> save(@ModelAttribute FoodForm foodForm) {
        MultipartFile img = foodForm.getImg();
        String fileName = img.getOriginalFilename();
        long currentTime = System.currentTimeMillis();
        fileName = currentTime + fileName;
        Long idMax = 0L;
        Food foodMaxId = foodService.findfoodMaxId();
        if (foodMaxId != null) {
            idMax = foodMaxId.getId();
        }
        Long curentID = idMax + 1;
        Date date = new Date();
        Food food = new Food(curentID, foodForm.getName(), fileName, foodForm.getDescription(), foodForm.getPrice(), foodForm.getSalePrice(), foodForm.getServiceFee(), date, date, 0L, 0L, foodForm.getCategory());
        foodService.save(food);
        try {
            FileCopyUtils.copy(img.getBytes(), new File(uploadPath + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }


        Image imageFood = new Image(fileName, food);
        imageService.save(imageFood);

        List<MultipartFile> images = foodForm.getImages();
        if (images.size() > 0) {
            for (MultipartFile image : images) {
                fileName = image.getOriginalFilename();
                currentTime = System.currentTimeMillis();
                fileName = currentTime + fileName;
                try {
                    FileCopyUtils.copy(image.getBytes(), new File(uploadPath + fileName));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                imageFood = new Image(fileName, food);
                imageService.save(imageFood);
            }
            return new ResponseEntity<>(foodService.save(food), HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/{id}")
    public ResponseEntity<Food> updateFood(@PathVariable Long id, @ModelAttribute FoodForm newfoodForm) {
        Optional<Food> optionalFood = foodService.findById(id);
        if (!optionalFood.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        MultipartFile img = newfoodForm.getImg();
        String fileName = img.getOriginalFilename();
        long currentTime = System.currentTimeMillis();
        fileName = currentTime + fileName;
        Date dayChange = new Date();
        try {
            FileCopyUtils.copy(img.getBytes(), new File(uploadPath + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Food food = optionalFood.get();
        food.setName(newfoodForm.getName());
        food.setImg(fileName);
        food.setDescription(newfoodForm.getDescription());
        food.setPrice(newfoodForm.getPrice());
        food.setSalePrice(newfoodForm.getSalePrice());
        food.setServiceFee(newfoodForm.getServiceFee());
        food.setDayCreate(food.getDayCreate());
        food.setDayChange(dayChange);
        food.setCountBuys(food.getCountBuys());
        food.setCountViews(food.getCountViews());
        foodService.save(food);


        Image imageFood = new Image(fileName, food);
        imageService.save(imageFood);

        List<MultipartFile> images = newfoodForm.getImages();
        if (images.size() > 0) {
            for (MultipartFile image : images) {
                fileName = image.getOriginalFilename();
                currentTime = System.currentTimeMillis();
                fileName = currentTime + fileName;
                try {
                    FileCopyUtils.copy(image.getBytes(), new File(uploadPath + fileName));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                imageFood = new Image(fileName, food);
                imageService.save(imageFood);
            }
        }
        return new ResponseEntity<>(foodService.save(food), HttpStatus.OK);
    }


        @DeleteMapping("/{id}")
        public ResponseEntity<Food> deleteProduct (@PathVariable Long id){
            Optional<Food> productOptional = foodService.findById(id);
            if (!productOptional.isPresent()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            foodService.removeById(id);
            return new ResponseEntity<>(productOptional.get(), HttpStatus.OK);
        }
}
