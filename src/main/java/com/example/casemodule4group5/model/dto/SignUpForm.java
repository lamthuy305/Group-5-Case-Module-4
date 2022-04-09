package com.example.casemodule4group5.model.dto;

import com.example.casemodule4group5.model.entity.Restaurant;
import com.example.casemodule4group5.model.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpForm {
    private String name;

    private String email;

    private String password;

    private String confirmPassword;

    private Restaurant restaurant;

    private List<Role> roles;
}
