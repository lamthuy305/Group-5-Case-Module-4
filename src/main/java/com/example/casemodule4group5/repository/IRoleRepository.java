package com.example.casemodule4group5.repository;

import com.example.casemodule4group5.model.entity.Role;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRoleRepository extends PagingAndSortingRepository<Role,Long> {
}
