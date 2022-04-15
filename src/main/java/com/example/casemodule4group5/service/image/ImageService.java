package com.example.casemodule4group5.service.image;

import com.example.casemodule4group5.model.entity.Image;
import com.example.casemodule4group5.repository.IImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ImageService implements IImageService {
    @Autowired
    private IImageRepository imageRepository;


    @Override
    public Page<Image> findAll(Pageable pageable) {
        return imageRepository.findAll(pageable);
    }


    @Override
    public Optional<Image> findById(Long id) {
        return imageRepository.findById(id);
    }

    @Override
    public Image save(Image image) {
        return imageRepository.save(image);
    }

    @Override
    public void removeById(Long id) {
        imageRepository.deleteById(id);
    }


    @Override
    public Iterable<Image> findImageByFoodId(Long id) {
        return imageRepository.findImageByFoodId(id);
    }

    @Override
    public Iterable<Image> findAll() {
        return imageRepository.findAll();
    }

}
