package com.example.casemodule4group5.repostory;

import com.example.casemodule4group5.model.entity.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends PagingAndSortingRepository<User, Long> {
    User findByEmail(String email);
}
