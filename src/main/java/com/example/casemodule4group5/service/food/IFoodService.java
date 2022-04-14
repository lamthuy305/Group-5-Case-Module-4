package com.example.casemodule4group5.service.food;

import com.example.casemodule4group5.model.entity.Food;
import com.example.casemodule4group5.service.IGeneralService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IFoodService extends IGeneralService<Food> {
    Page<Food> findFoodByNameContaining(String name, Pageable pageable);

    Food findfoodMaxId();

    Page<Food> findAllFoodByTag(String slug, Pageable pageable);


}
