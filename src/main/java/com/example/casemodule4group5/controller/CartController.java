package com.example.casemodule4group5.controller;

import com.example.casemodule4group5.model.entity.Cart;
import com.example.casemodule4group5.service.cart.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@CrossOrigin("*")
@RequestMapping("/carts")
public class CartController {
    @Autowired
    private ICartService cartService;

    @GetMapping
    public ResponseEntity<Page<Cart>> findAll(@RequestParam(name = "id") Optional<Long> id, @PageableDefault(20) Pageable pageable) {
        Page<Cart> carts = cartService.findAll(pageable);
        if (id.isPresent()) {
            carts = cartService.findCartByOrderId(id.get(), pageable);
        }
        return new ResponseEntity<>(carts, HttpStatus.OK);

    }

    @GetMapping("/order/{id}")
    public ResponseEntity<Iterable<Cart>> findCartByOrderId(@PathVariable Long id) {
        Iterable<Cart> carts = cartService.findCartByOrderId(id);
        return new ResponseEntity<>(carts, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Cart> save(@RequestBody Cart cart) {
        return new ResponseEntity<>(cartService.save(cart), HttpStatus.CREATED);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Cart> findById(@PathVariable Long id) {
        Optional<Cart> cartOptional = cartService.findById(id);
        if (!cartOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(cartOptional.get(), HttpStatus.OK);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Cart> updateOrder(@PathVariable Long id, @RequestBody Cart cart) {
        Optional<Cart> cartOptional = cartService.findById(id);
        if (!cartOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        cart.setId(id);
        return new ResponseEntity<>(cartService.save(cart), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Cart> deleteOrder(@PathVariable Long id) {
        Optional<Cart> cartOptional = cartService.findById(id);
        if (!cartOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        cartService.removeById(id);
        return new ResponseEntity<>(cartOptional.get(), HttpStatus.OK);
    }
}

