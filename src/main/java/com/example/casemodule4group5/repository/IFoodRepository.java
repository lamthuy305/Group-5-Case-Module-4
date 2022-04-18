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

    @Query(value = "select * from foods order by foods.count_buys DESC limit 9", nativeQuery = true)
    Iterable<Food> findFoodByTopBuy();

    @Query(value = "select * from foods order by foods.sale_price DESC limit 9", nativeQuery = true)
    Iterable<Food> findFoodByTopSale();

    @Query(value = "select * from foods where category_id = ?1", nativeQuery = true)
    Page<Food> findFoodByCategoryId(Long id, Pageable pageable);

    @Query(value = "select * from foods where user_id = ?1", nativeQuery = true)
    Page<Food> findAllFoodByUserId(Long id, Pageable pageable);

    @Query(value = "select * from foods where (user_id = ?1 and name like ?2)", nativeQuery = true)
    Page<Food> findAllFoodByUserIdContaining(Long id, String name, Pageable pageable);

}
