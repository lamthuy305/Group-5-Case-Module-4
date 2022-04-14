package com.example.casemodule4group5.service.tag;

import com.example.casemodule4group5.model.entity.Tag;
import com.example.casemodule4group5.repostory.ITagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TagService implements ITagService {
    @Autowired
    private ITagRepository tagRepository;

    @Override
    public Page<Tag> findAll(Pageable pageable) {
        return tagRepository.findAll(pageable);
    }

    @Override
    public Optional<Tag> findById(Long id) {
        return tagRepository.findById(id);
    }

    @Override
    public Tag save(Tag tag) {
        return tagRepository.save(tag);
    }

    @Override
    public void removeById(Long id) {
        tagRepository.deleteById(id);
    }

    @Override
    public Page<Tag> findTagByNameContaining(String name, Pageable pageable) {
        return tagRepository.findTagByNameContaining(name, pageable);
    }
}
