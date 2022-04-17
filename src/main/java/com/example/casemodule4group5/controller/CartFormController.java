package com.example.casemodule4group5.controller;

import com.example.casemodule4group5.model.dto.CartForm;
import com.example.casemodule4group5.service.cartform.ICartFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/cartForm")
public class CartFormController {
    @Autowired
    private ICartFormService cartFormService;

    @GetMapping
    public ResponseEntity<Iterable<CartForm>> finAll() {
        Iterable<CartForm> cartForms = cartFormService.findAllCartForm();
        return new ResponseEntity<>(cartForms, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Iterable<CartForm>> findAllCartFormByUserId(@PathVariable Long id) {
        Iterable<CartForm> cartForms = cartFormService.findAllCartFormByUserId(id);
        return new ResponseEntity<>(cartForms, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CartForm> update(@PathVariable Long id, @RequestBody CartForm cartForm) {
        Optional<CartForm> cartFormOptional = cartFormService.findById(id);
        if (!cartFormOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        cartForm.setId(id);
        cartFormService.save(cartForm);
        return new ResponseEntity<>(cartForm, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CartForm> create(@RequestBody CartForm cartForm) {
        Iterable<CartForm> cartForms = cartFormService.findAllCartFormByUserId(cartForm.getUser().getId());
        for (CartForm cartform1 : cartForms) {
            if (cartform1.getFood().getId().equals(cartForm.getFood().getId())) {
                double quantity = cartform1.getQuantity();
                cartform1.setQuantity(quantity + 1);
                return new ResponseEntity<>(cartFormService.save(cartform1), HttpStatus.OK);
            }
        }
        cartForm.setQuantity(1);
        cartFormService.save(cartForm);
        return new ResponseEntity<>(cartForm, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CartForm> delete(@PathVariable Long id) {
        Optional<CartForm> cartFormOptional = cartFormService.findById(id);
        if (!cartFormOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        cartFormService.removeById(id);
        return new ResponseEntity<>(cartFormOptional.get(), HttpStatus.OK);
    }
}
