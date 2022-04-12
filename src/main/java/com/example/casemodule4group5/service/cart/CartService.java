package com.example.casemodule4group5.service.cart;

import com.example.casemodule4group5.model.entity.Cart;
import com.example.casemodule4group5.repostory.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartService implements ICartService {
    @Autowired
    private CartRepository orderDetailRepository;

    @Override
    public Page<Cart> findAll(Pageable pageable) {
        return orderDetailRepository.findAll(pageable);
    }

    @Override
    public Optional<Cart> findById(Long id) {
        return orderDetailRepository.findById(id);
    }

    @Override
    public Cart save(Cart orderDetail) {
        return orderDetailRepository.save(orderDetail);
    }

    @Override
    public void removeById(Long id) {
        orderDetailRepository.deleteById(id);
    }
}
