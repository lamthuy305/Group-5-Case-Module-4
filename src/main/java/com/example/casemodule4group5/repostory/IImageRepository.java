package com.example.casemodule4group5.repostory;

import com.example.casemodule4group5.model.entity.Image;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IImageRepository extends PagingAndSortingRepository<Image, Long> {
}
