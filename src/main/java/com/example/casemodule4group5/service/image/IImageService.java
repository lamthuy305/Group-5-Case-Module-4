package com.example.casemodule4group5.service.image;

import com.example.casemodule4group5.model.entity.Image;
import com.example.casemodule4group5.service.IGeneralService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface IImageService extends IGeneralService<Image> {

    Iterable<Image> findImageByFoodId(Long id);

    Iterable<Image> findAll();
}
