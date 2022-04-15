package com.example.casemodule4group5.service.order;

import com.example.casemodule4group5.model.entity.Order;
import com.example.casemodule4group5.repository.IOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderService implements IOrderService {
    @Autowired
    private IOrderRepository orderRepository;

    @Override
    public Page<Order> findAll(Pageable pageable) {
        return orderRepository.findAll(pageable);
    }

    @Override
    public Optional<Order> findById(Long id) {
        return orderRepository.findById(id);
    }

    @Override
    public Order save(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public void removeById(Long id) {
        orderRepository.deleteById(id);
    }

    @Override
    public Page<Order> findOrderByEmail(String email, Pageable pageable) {
        email = "%" + email + "%";
        return orderRepository.findOrderByEmail(email, pageable);
    }
}
