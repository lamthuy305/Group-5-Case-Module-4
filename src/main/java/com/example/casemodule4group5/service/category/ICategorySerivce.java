package com.example.casemodule4group5.service.category;

import com.example.casemodule4group5.model.entity.Category;
import com.example.casemodule4group5.repostory.IFoodCount;
import com.example.casemodule4group5.service.IGeneralService;

import java.util.List;

public interface ICategorySerivce extends IGeneralService<Category> {
    Iterable<Category> findAll();
    List<IFoodCount> countTotalFoodOfCategoryInterface();
    Iterable<Category> findAllByNameContaining(String name);
}
