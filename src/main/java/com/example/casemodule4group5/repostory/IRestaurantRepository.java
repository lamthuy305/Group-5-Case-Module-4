package com.example.casemodule4group5.repostory;

import com.example.casemodule4group5.model.entity.Restaurant;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRestaurantRepository extends PagingAndSortingRepository<Restaurant, Long> {

}
