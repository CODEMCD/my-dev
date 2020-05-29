package com.codemcd.mydev.linkbucket.service;

import com.codemcd.mydev.linkbucket.domain.Tag;
import com.codemcd.mydev.linkbucket.domain.TagRepository;
import com.codemcd.mydev.linkbucket.service.dto.TagResponseDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TagService {
    private final TagRepository tagRepository;

    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    @Transactional
    public Tag saveOrGet(String tagName) {
        return tagRepository.findByName(tagName)
                .orElseGet(() -> tagRepository.save(Tag.builder().name(tagName).build()));
    }

    public List<TagResponseDto> findAll() {
        return null;
    }
}
