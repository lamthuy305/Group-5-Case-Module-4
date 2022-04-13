package com.example.casemodule4group5.repostory;

import com.example.casemodule4group5.model.entity.Image;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IImageRepository extends PagingAndSortingRepository<Image, Long> {
   @Query(value = "select * from images i join foods on i.food_id = foods.id where food_id=?1",nativeQuery = true)
   Page<Image> findImageByFoodId(Long id, Pageable pageable);
}
