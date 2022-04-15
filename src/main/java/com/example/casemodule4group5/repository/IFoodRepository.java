package com.example.casemodule4group5.repository;

import com.example.casemodule4group5.model.entity.Food;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IFoodRepository extends PagingAndSortingRepository<Food, Long> {
    Page<Food> findFoodByNameContaining(String name, Pageable pageable);

    @Query(value = "select * from foods where id = (select MAX(id) from foods)", nativeQuery = true)
    Food findfoodMaxId();


    @Query(value = "select * from foods f join food_tag ft on f.id = ft.food_id where tag_id = (select id from tags where slug =?1)", nativeQuery = true)
    Page<Food> findAllFoodByTag(String slug, Pageable pageable);






}
