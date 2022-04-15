package com.example.casemodule4group5.validator;

import com.example.casemodule4group5.model.entity.User;
import com.example.casemodule4group5.repository.IUserRepository;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail,String> {
    private IUserRepository userRepository;

    public UniqueEmailValidator(IUserRepository userRepository) {
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
