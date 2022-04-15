package com.example.casemodule4group5.repository;

import com.example.casemodule4group5.model.entity.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITagRepository extends PagingAndSortingRepository<Tag, Long> {
    Page<Tag> findTagByNameContaining(String name, Pageable pageable);
}
