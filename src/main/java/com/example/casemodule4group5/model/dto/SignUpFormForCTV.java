package com.example.casemodule4group5.model.dto;

import com.example.casemodule4group5.model.entity.Restaurant;
import com.example.casemodule4group5.model.entity.Role;
import com.example.casemodule4group5.validator.PasswordConfirm;
import com.example.casemodule4group5.validator.UniqueUsername;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpFormForCTV {
    private String name;

    @NotEmpty
    @Size(min = 6, max = 12)
    @UniqueUsername
    private String email;

    @PasswordConfirm
    private PasswordForm passwordForm;

    private Restaurant restaurant;

    private List<Role> roles;
}
