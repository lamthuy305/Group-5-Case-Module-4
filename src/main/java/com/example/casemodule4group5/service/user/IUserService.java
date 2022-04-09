package com.example.casemodule4group5.service.user;

import com.example.casemodule4group5.model.entity.User;
import com.example.casemodule4group5.service.IGeneralService;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface IUserService extends IGeneralService<User>, UserDetailsService {
    User findByEmail(String email);

    boolean checkRegexPassword(String password);
}
