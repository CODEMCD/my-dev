package com.codemcd.mydev.linkbucket.service;

import com.codemcd.mydev.linkbucket.domain.Link;
import com.codemcd.mydev.linkbucket.domain.LinkRepository;
import com.codemcd.mydev.linkbucket.domain.Tag;
import com.codemcd.mydev.linkbucket.service.dto.LinkRequestDto;
import com.codemcd.mydev.linkbucket.service.dto.LinkResponseDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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
                .collect(Collectors.toSet());

        tags.forEach(tag -> linkTagService.save(link, tag));

        return new LinkResponseDto(link.getId(), link.getUrl(), link.getTitle(), link.getDescription(),
                link.getImage(), tags.stream().map(Tag::getName).collect(Collectors.toList()));
    }

    @Transactional(readOnly = true)
    public List<LinkResponseDto> findAll() {
        List<Link> links = linkRepository.findAllJoinFetch();

        return links.stream()
                .map(link -> new LinkResponseDto(link.getId(), link.getUrl(), link.getTitle(),
                        link.getDescription(), link.getImage(),
                        link.getTags().stream().map(linkTag -> linkTag.getTag().getName()).collect(Collectors.toList())))
                .collect(Collectors.toList())
                ;
    }

    @Transactional
    public void deleteAll() {
        linkRepository.deleteAll();
    }
}
