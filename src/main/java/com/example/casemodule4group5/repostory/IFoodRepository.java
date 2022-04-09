package com.example.casemodule4group5.repostory;

import com.example.casemodule4group5.model.entity.Food;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IFoodRepository extends PagingAndSortingRepository<Food, Long> {

}
