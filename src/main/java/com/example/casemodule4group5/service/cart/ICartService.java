package com.example.casemodule4group5.service.cart;

import com.example.casemodule4group5.model.entity.Cart;
import com.example.casemodule4group5.service.IGeneralService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ICartService extends IGeneralService<Cart> {
    Page<Cart> findCartByOrderId(Long id, Pageable pageable);

    Iterable<Cart> findCartByOrderId(Long id);
}
