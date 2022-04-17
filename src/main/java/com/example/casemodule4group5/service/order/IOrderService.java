package com.example.casemodule4group5.service.order;

import com.example.casemodule4group5.model.entity.Order;
import com.example.casemodule4group5.service.IGeneralService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IOrderService extends IGeneralService<Order> {
    Page<Order> findOrderByEmail(String email, Pageable pageable);
    Page<Order> findOrderByUserId(Long id, Pageable pageable);
}
