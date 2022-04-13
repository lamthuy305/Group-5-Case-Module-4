package com.example.casemodule4group5.service.cart;

import com.example.casemodule4group5.model.entity.Cart;
import com.example.casemodule4group5.repostory.ICartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartService implements ICartService {
    @Autowired
    private ICartRepository cartRepository;

    @Override
    public Page<Cart> findAll(Pageable pageable) {
        return cartRepository.findAll(pageable);
    }

    @Override
    public Optional<Cart> findById(Long id) {
        return cartRepository.findById(id);
    }

    @Override
    public Cart save(Cart cart) {
        return cartRepository.save(cart);
    }

    @Override
    public void removeById(Long id) {
        cartRepository.deleteById(id);
    }
}
