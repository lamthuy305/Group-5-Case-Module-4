package com.example.casemodule4group5.repository;

import com.example.casemodule4group5.model.entity.Image;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IImageRepository extends PagingAndSortingRepository<Image, Long> {

    @Query(value = "select * from images i join foods on i.food_id = foods.id where food_id=?1", nativeQuery = true)
    Iterable<Image> findImageByFoodId(Long id);
}
