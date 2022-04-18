package com.example.casemodule4group5.controller;

import com.example.casemodule4group5.model.entity.Cart;
import com.example.casemodule4group5.model.entity.Image;
import com.example.casemodule4group5.model.entity.Order;
import com.example.casemodule4group5.model.entity.User;
import com.example.casemodule4group5.service.cart.ICartService;
import com.example.casemodule4group5.service.order.IOrderService;
import com.example.casemodule4group5.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;


@RestController
@CrossOrigin("*")
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private IOrderService orderService;

    @Autowired
    private ICartService cartService;

    @Autowired
    private IUserService userService;

    @GetMapping
    public ResponseEntity<Page<Order>> findAll(@RequestParam(name = "q") Optional<String> q, @PageableDefault(5) Pageable pageable) {
        Page<Order> orders = orderService.findAll(pageable);
        if (q.isPresent()) {
            orders = orderService.findOrderByEmail(q.get(), pageable);
        }
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping("/userid/{id}")
    public ResponseEntity<Page<Order>> findAllOrderByUserId(@PathVariable(name = "id") Long id, @PageableDefault(5) Pageable pageable) {
        Page<Order> orders = orderService.findAllOrderByUserId(id, pageable);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<Order> save(@RequestBody Order order) {
        return new ResponseEntity<>(orderService.save(order), HttpStatus.CREATED);
    }

    @PostMapping("/create")
    public ResponseEntity<Order> createOrder(@RequestParam(name = "id") Long id) {
        Optional<User> user = userService.findById(id);
        if (!user.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Date date = new Date();
        Order order = new Order(user.get(), date);
        orderService.save(order);
        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Order> findById(@PathVariable Long id) {
        Optional<Order> productOptional = orderService.findById(id);
        if (!productOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(productOptional.get(), HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<Page<Order>> findOrderByUserId(@PathVariable Long id, Pageable pageable) {
        Page<Order> orders = orderService.find6Order(id, pageable);
        return new ResponseEntity<>(orders, HttpStatus.OK);
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

