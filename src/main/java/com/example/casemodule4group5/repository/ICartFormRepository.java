package com.example.casemodule4group5.repository;

import com.example.casemodule4group5.model.dto.CartForm;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICartFormRepository extends PagingAndSortingRepository<CartForm, Long> {

    @Query(value = "select * from cart_form where user_id =?1", nativeQuery = true)
    Iterable<CartForm> findAllCartFormByUserId(Long id);
}
