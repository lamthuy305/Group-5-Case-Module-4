package com.example.casemodule4group5.repostory;

import com.example.casemodule4group5.model.entity.Category;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ICategoryRepository extends PagingAndSortingRepository<Category,Long> {
}
