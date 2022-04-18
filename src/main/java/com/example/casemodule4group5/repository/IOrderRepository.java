package com.example.casemodule4group5.repository;

import com.example.casemodule4group5.model.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOrderRepository extends PagingAndSortingRepository<Order, Long> {
    @Query(value = "select * from orders o join users on o.user_id = users.id where users.email like ?1", nativeQuery = true)
    Page<Order> findOrderByEmail(String email, Pageable pageable);

    @Query(value = "select * from orders o join users on o.user_id = users.id where users.id = ?1", nativeQuery = true)
    Page<Order> findOrderByUserId(Long id, Pageable pageable);

    @Query(value = "select * from orders o join users on o.user_id = users.id where users.id = ?1 order by o.id DESC", nativeQuery = true)
    Page<Order> find6Order(Long id, Pageable pageable);

    @Query(value = "select * from orders o join users on o.user_id = users.id where users.id = ?1", nativeQuery = true)
    Page<Order> findAllOrderByUserId(Long id, Pageable pageable);

}
