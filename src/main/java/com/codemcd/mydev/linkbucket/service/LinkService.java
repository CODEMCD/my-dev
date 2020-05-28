package com.codemcd.mydev.linkbucket.service;

import com.codemcd.mydev.linkbucket.domain.Link;
import com.codemcd.mydev.linkbucket.domain.LinkRepository;
import com.codemcd.mydev.linkbucket.domain.Tag;
import com.codemcd.mydev.linkbucket.service.dto.LinkRequestDto;
import com.codemcd.mydev.linkbucket.service.dto.LinkResponseDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class LinkService {
    private final LinkRepository linkRepository;
    private final TagService tagService;
    private final LinkTagService linkTagService;

    public LinkService(LinkRepository linkRepository,
                       TagService tagService,
                       LinkTagService linkTagService) {
        this.linkRepository = linkRepository;
        this.tagService = tagService;
        this.linkTagService = linkTagService;
    }

    @Transactional
    public LinkResponseDto save(LinkRequestDto linkRequestDto) {
        Link link = Link.builder()
                .url(linkRequestDto.getUrl())
                .title(linkRequestDto.getTitle())
                .description(linkRequestDto.getDescription())
                .image((linkRequestDto.getImage())).build();

        linkRepository.save(link);

        Set<Tag> tags = linkRequestDto.getTags().stream()
                .map(tagService::saveOrGet)
                .collect(Collectors.toSet())
                ;

        tags.forEach(tag -> linkTagService.save(link, tag));

        return new LinkResponseDto(link.getId());
    }
}
