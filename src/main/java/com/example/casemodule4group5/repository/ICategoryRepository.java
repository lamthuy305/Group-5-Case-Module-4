package com.example.casemodule4group5.repository;

import com.example.casemodule4group5.model.entity.Category;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICategoryRepository extends PagingAndSortingRepository<Category, Long> {
    @Query(value = "select category_id, categories.image,categories.name,count(foods.id) as 'totalFood' from categories join foods on categories.id = foods.category_id group by categories.image, categories.name", nativeQuery = true)
    List<IFoodCount> countTotalFoodOfCategoryInterface();

    Iterable<Category> findAllByNameContaining(String name);

}
