package com.example.casemodule4group5.controller;

import com.example.casemodule4group5.model.entity.Cart;
import com.example.casemodule4group5.model.entity.Image;
import com.example.casemodule4group5.model.entity.Order;
import com.example.casemodule4group5.service.cart.ICartService;
import com.example.casemodule4group5.service.order.IOrderService;
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
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    IOrderService orderService;

    @Autowired
    ICartService cartService;

    @GetMapping
    public ResponseEntity<Page<Order>> findAll(@RequestParam(name = "q") Optional<String> q, @PageableDefault(20) Pageable pageable) {
        Page<Order> orders = orderService.findAll(pageable);
        if (q.isPresent()) {
            orders = orderService.findOrderByEmail(q.get(), pageable);
        }
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Order> save(@RequestBody Order order) {
        return new ResponseEntity<>(orderService.save(order), HttpStatus.CREATED);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Order> findById(@PathVariable Long id) {
        Optional<Order> productOptional = orderService.findById(id);
        if (!productOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(productOptional.get(), HttpStatus.OK);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable Long id, @RequestBody Order order) {
        Optional<Order> productOptional = orderService.findById(id);
        if (!productOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        order.setId(id);
        return new ResponseEntity<>(orderService.save(order), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Order> deleteOrder(@PathVariable Long id, Pageable pageable) {
        Optional<Order> productOptional = orderService.findById(id);
        if (!productOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Page<Cart> carts = cartService.findCartByOrderId(id, pageable);
        for (Cart cart : carts) {
            cartService.removeById(cart.getId());
        }
        orderService.removeById(id);
        return new ResponseEntity<>(productOptional.get(), HttpStatus.OK);
    }
}

