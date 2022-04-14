package com.example.casemodule4group5.controller;

import com.example.casemodule4group5.model.dto.JwtResponse;
import com.example.casemodule4group5.model.dto.SignUpForm;
import com.example.casemodule4group5.model.entity.User;
import com.example.casemodule4group5.service.JwtService;
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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
@CrossOrigin("*")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private IUserService userService;

    @Autowired
    private JwtService jwtService;


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
        if (bindingResult.hasFieldErrors()){
            return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if (!signUpForm.getPasswordForm().getPassword().equals(signUpForm.getPasswordForm().getConfirmPassword()) || userService.checkRegexPassword(signUpForm.getPasswordForm().getPassword()) || userService.checkRegexEmail(signUpForm.getEmail())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        User user = new User(signUpForm.getName(), signUpForm.getEmail(), signUpForm.getPasswordForm().getPassword(), signUpForm.getRoles());
        return new ResponseEntity<>(userService.save(user), HttpStatus.CREATED);
    }

}
