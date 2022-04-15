package com.example.casemodule4group5.repostory;

import com.example.casemodule4group5.model.entity.Cart;
import com.example.casemodule4group5.model.entity.Image;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICartRepository extends PagingAndSortingRepository<Cart, Long> {

    @Query(value = "select * from carts join orders o on o.id = carts.order_id where order_id =?1", nativeQuery = true)
    Page<Cart> findCartByOrderId(Long id, Pageable pageable);
}
