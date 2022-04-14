package com.example.casemodule4group5.service.tag;

import com.example.casemodule4group5.model.entity.Food;
import com.example.casemodule4group5.model.entity.Tag;
import com.example.casemodule4group5.service.IGeneralService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ITagService extends IGeneralService<Tag> {
    Page<Tag> findTagByNameContaining(String name, Pageable pageable);
}
