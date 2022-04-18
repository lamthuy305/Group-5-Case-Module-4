package com.example.casemodule4group5.service.cartform;

import com.example.casemodule4group5.model.dto.CartForm;
import com.example.casemodule4group5.repository.ICartFormRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartSFormervice implements ICartFormService {
    @Autowired
    private ICartFormRepository cartFormRepository;

    @Override
    public Page<CartForm> findAll(Pageable pageable) {
        return cartFormRepository.findAll(pageable);
    }


    @Override
    public Optional<CartForm> findById(Long id) {
        return cartFormRepository.findById(id);
    }

    @Override
    public CartForm save(CartForm cartForm) {
        return cartFormRepository.save(cartForm);
    }

    @Override
    public void removeById(Long id) {
        cartFormRepository.deleteById(id);
    }

    @Override
    public Iterable<CartForm> findAllCartForm() {
        return cartFormRepository.findAll();
    }

    @Override
    public Iterable<CartForm> findAllCartFormByUserId(Long id) {
        return cartFormRepository.findAllCartFormByUserId(id);
    }
}
