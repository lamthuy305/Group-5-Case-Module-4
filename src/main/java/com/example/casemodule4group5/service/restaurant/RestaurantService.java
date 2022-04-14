package com.example.casemodule4group5.service.restaurant;

import com.example.casemodule4group5.model.entity.Restaurant;
import com.example.casemodule4group5.repostory.IRestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RestaurantService implements IRestaurantService {
    @Autowired
    private IRestaurantRepository restaurantRepository;

    @Override
    public Page<Restaurant> findAll(Pageable pageable) {
        return restaurantRepository.findAll(pageable);
    }

    @Override
    public Optional<Restaurant> findById(Long id) {
        return restaurantRepository.findById(id);
    }

    @Override
    public Restaurant save(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    @Override
    public void removeById(Long id) {
        restaurantRepository.deleteById(id);
    }

    @Override
    public Page<Restaurant> findAllByName(String name, Pageable pageable) {
        return restaurantRepository.findAllByName(name, pageable);
    }

    @Override
    public Restaurant findRestaurantMaxID() {
        return restaurantRepository.findRestaurantMaxID();
    }
}
