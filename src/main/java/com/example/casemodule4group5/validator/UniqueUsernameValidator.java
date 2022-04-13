package com.example.casemodule4group5.validator;

import com.example.casemodule4group5.model.entity.User;
import com.example.casemodule4group5.repostory.IUserRepository;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername,String> {
    private IUserRepository userRepository;

    public UniqueUsernameValidator(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        User user = userRepository.findByEmail(email);
        if(user!=null){
            return false;
        }
        return true;
    }
}
