package com.example.casemodule4group5.repostory;

import com.example.casemodule4group5.model.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IOrderRepository extends PagingAndSortingRepository<Order, Long> {
    Page<Order> findOrdersByUserId(Long id, Pageable pageable);
}
