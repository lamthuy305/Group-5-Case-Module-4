package com.example.casemodule4group5.service.category;

import com.example.casemodule4group5.model.entity.Category;
import com.example.casemodule4group5.repository.ICategoryRepository;
import com.example.casemodule4group5.repository.IFoodCount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService implements ICategorySerivce {
    @Autowired
    private ICategoryRepository categoryRepository;


    @Override
    public Page<Category> findAll(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }

    @Override
    public Optional<Category> findById(Long id) {
        return categoryRepository.findById(id);
    }

    @Override
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public void removeById(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public Iterable<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public List<IFoodCount> countTotalFoodOfCategoryInterface() {
        return categoryRepository.countTotalFoodOfCategoryInterface();
    }

    @Override
    public Iterable<Category> findAllByNameContaining(String name) {
        return categoryRepository.findAllByNameContaining(name);
    }

}
