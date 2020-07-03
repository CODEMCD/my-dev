package com.codemcd.mydev.linkbucket.service;

import com.codemcd.mydev.linkbucket.domain.LinkTag;
import com.codemcd.mydev.linkbucket.domain.Tag;
import com.codemcd.mydev.linkbucket.domain.TagRepository;
import com.codemcd.mydev.linkbucket.service.dto.LinkResponseDto;
import com.codemcd.mydev.linkbucket.service.dto.TagResponseDto;
import com.codemcd.mydev.linkbucket.service.exception.NotFoundTagNameException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TagService {
    private final TagRepository tagRepository;
    private final LinkTagService linkTagService;

    public TagService(TagRepository tagRepository, LinkTagService linkTagService) {
        this.tagRepository = tagRepository;
        this.linkTagService = linkTagService;
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

    @Transactional(readOnly = true)
    public List<LinkResponseDto> find(String tagName) {
        Tag tag = tagRepository.findByNameJoinFetch(tagName)
                .orElseThrow(() -> new NotFoundTagNameException("유효하지 않는 Tag Name 입니다!"));

        return tag.getLinks().stream()
                .map(LinkTag::getLink)
                .map(link -> new LinkResponseDto(link.getId(), link.getUrl(), link.getTitle(),
                        link.getDescription(), link.getImage(),
                        linkTagService.findTagsByLink(link).stream().map(Tag::getName).collect(Collectors.toList())))
                .collect(Collectors.toList())
                ;
    }
}
