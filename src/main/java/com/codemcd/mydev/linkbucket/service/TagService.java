package com.codemcd.mydev.linkbucket.service;

import com.codemcd.mydev.linkbucket.domain.Tag;
import com.codemcd.mydev.linkbucket.domain.TagRepository;
import com.codemcd.mydev.linkbucket.service.dto.TagResponseDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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

    @Transactional(readOnly = true)
    public List<TagResponseDto> findAll() {
        List<Tag> tags = tagRepository.findAllJoinFetch();

        return tags.stream()
                .map(tag -> new TagResponseDto(tag.getId(), tag.getName(), tag.getLinks().size()))
                .collect(Collectors.toList())
                ;
    }
}
