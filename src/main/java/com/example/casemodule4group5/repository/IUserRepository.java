package com.example.casemodule4group5.repository;

import com.example.casemodule4group5.model.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends PagingAndSortingRepository<User, Long> {
    User findByEmail(String email);

    @Query(value = "update user_role set role_id = ?1 where user_id = ?2",nativeQuery = true)
    User updateRole(Long roleId, Long userId);
}
