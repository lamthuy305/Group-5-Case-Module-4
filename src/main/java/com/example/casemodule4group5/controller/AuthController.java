package com.example.casemodule4group5.controller;

import com.example.casemodule4group5.model.dto.JwtResponse;

import com.example.casemodule4group5.model.dto.RestaurantForm;
import com.example.casemodule4group5.model.dto.SignUpForm;

import com.example.casemodule4group5.model.entity.Food;
import com.example.casemodule4group5.model.entity.Restaurant;
import com.example.casemodule4group5.model.entity.User;
import com.example.casemodule4group5.service.JwtService;
import com.example.casemodule4group5.service.restaurant.IRestaurantService;
import com.example.casemodule4group5.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.Optional;


@RestController
@CrossOrigin("*")
public class AuthController {
    @Autowired
    private IRestaurantService restaurantService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private IUserService userService;

    @Autowired
    private JwtService jwtService;


    @GetMapping("/users")
    public ResponseEntity<Iterable<User>> findAll() {
        Iterable<User> users = userService.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtService.generateTokenLogin(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User currentUser = userService.findByEmail(user.getEmail());
        return ResponseEntity.ok(new JwtResponse(jwt, currentUser.getId(), userDetails.getUsername(), userDetails.getAuthorities()));
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@Valid @RequestBody SignUpForm signUpForm, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if (!signUpForm.getPasswordForm().getPassword().equals(signUpForm.getPasswordForm().getConfirmPassword()) || !userService.checkRegexPassword(signUpForm.getPasswordForm().getPassword()) || !userService.checkRegexEmail(signUpForm.getEmail())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        User user = new User(signUpForm.getName(), signUpForm.getEmail(), signUpForm.getPasswordForm().getPassword(), signUpForm.getRoles());
        return new ResponseEntity<>(userService.save(user), HttpStatus.CREATED);
    }

    @PostMapping("/registerCTV")
    public ResponseEntity<User> registerCTV(@RequestParam Long id,@ModelAttribute RestaurantForm restaurantForm) {
        Optional<User> user = userService.findById(id);
        if (!user.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Long idMax = 0L;
        Restaurant restaurantMaxId = restaurantService.findRestaurantMaxID();
        if (restaurantMaxId != null) {
            idMax = restaurantMaxId.getId();
        }
        Long curentID = idMax + 1;

        MultipartFile img = restaurantForm.getImg();
        String fileName = img.getOriginalFilename();
        long currentTime = System.currentTimeMillis();
        fileName = currentTime + fileName;
        String name = restaurantForm.getName();
        String address = restaurantForm.getAddress();
        String openTime = restaurantForm.getOpenTime();
        String closeTime = restaurantForm.getCloseTime();
        Restaurant restaurant = new Restaurant(curentID, name, fileName, address, openTime, closeTime);
        restaurantService.save(restaurant);
        user.get().setRestaurant(restaurant);
        return new ResponseEntity<>(userService.saveCTV(user.get()), HttpStatus.OK);
    }


}
