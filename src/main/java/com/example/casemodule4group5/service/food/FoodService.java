package com.example.casemodule4group5.service.food;

import com.example.casemodule4group5.model.entity.Food;
import com.example.casemodule4group5.repository.IFoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FoodService implements IFoodService {
    @Autowired
    private IFoodRepository foodRepository;

    @Override
    public Page<Food> findAll(Pageable pageable) {
        return foodRepository.findAll(pageable);
    }

    @Override
    public Optional<Food> findById(Long id) {
        return foodRepository.findById(id);
    }

    @Override
    public Food save(Food food) {
        return foodRepository.save(food);
    }

    @Override
    public void removeById(Long id) {
        foodRepository.deleteById(id);
    }

    @Override
    public Page<Food> findFoodByNameContaining(String name, Pageable pageable) {
        return foodRepository.findFoodByNameContaining(name, pageable);
    }

    @Override
    public Food findfoodMaxId() {
        return foodRepository.findfoodMaxId();
    }

    @Override
    public Page<Food> findAllFoodByTag(String slug, Pageable pageable) {
        return foodRepository.findAllFoodByTag(slug, pageable);
    }

    @Override
    public Iterable<Food> findFoodByTopBuy() {
        return foodRepository.findFoodByTopBuy();
    }

    @Override
    public Iterable<Food> findFoodByTopSale() {
        return foodRepository.findFoodByTopSale();
    }

    @Override
    public Page<Food> findFoodByCategoryId(Long id, Pageable pageable) {
        return foodRepository.findFoodByCategoryId(id, pageable);
    }

    @Override
    public Page<Food> findAllFoodByUserId(Long id, Pageable pageable) {
        return foodRepository.findAllFoodByUserId(id, pageable);
    }

    @Override
    public Page<Food> findAllFoodByUserIdContaining(Long id, String name, Pageable pageable) {
        name = "%" + name + "%";
        return foodRepository.findAllFoodByUserIdContaining(id, name, pageable);
    }

}
