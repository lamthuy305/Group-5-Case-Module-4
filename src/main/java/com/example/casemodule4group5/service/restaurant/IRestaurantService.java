package com.example.casemodule4group5.service.restaurant;

import com.example.casemodule4group5.model.entity.Restaurant;
import com.example.casemodule4group5.service.IGeneralService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IRestaurantService extends IGeneralService<Restaurant> {
    Page<Restaurant> findAllByName(String name, Pageable pageable);
    Restaurant findRestaurantMaxID();
}
