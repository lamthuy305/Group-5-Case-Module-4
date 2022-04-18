package com.example.casemodule4group5.service.food;

import com.example.casemodule4group5.model.entity.Food;
import com.example.casemodule4group5.service.IGeneralService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IFoodService extends IGeneralService<Food> {
    Page<Food> findFoodByNameContaining(String name, Pageable pageable);

    Food findfoodMaxId();

    Page<Food> findAllFoodByTag(String slug, Pageable pageable);

    Iterable<Food> findFoodByTopBuy();

    Iterable<Food> findFoodByTopSale();

    Page<Food> findFoodByCategoryId(Long id, Pageable pageable);

    Page<Food> findAllFoodByUserId(Long id, Pageable pageable);

    Page<Food> findAllFoodByUserIdContaining(Long id, String name, Pageable pageable);

}
