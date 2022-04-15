package com.example.casemodule4group5.repository;

import com.example.casemodule4group5.model.entity.Restaurant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRestaurantRepository extends PagingAndSortingRepository<Restaurant, Long> {
    Page<Restaurant> findAllByName(String name, Pageable pageable);

    @Query(value = "select * from restaurants where id = (select MAX(id) from restaurants)", nativeQuery = true)
    Restaurant findRestaurantMaxID();
}
