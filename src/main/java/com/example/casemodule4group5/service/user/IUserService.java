package com.example.casemodule4group5.service.user;

import com.example.casemodule4group5.model.entity.User;
import com.example.casemodule4group5.service.IGeneralService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface IUserService extends IGeneralService<User>, UserDetailsService {
    Page<User> findAll(Pageable pageable);

    User findByEmail(String email);

    boolean checkRegexPassword(String password);

    boolean checkRegexEmail(String email);

    User saveCTV(User user);
    boolean checkRegexPhoneNumber(String phoneNumber);
}
