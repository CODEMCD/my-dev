package com.codemcd.mydev.linkbucket.service;

import com.codemcd.mydev.linkbucket.domain.Link;
import com.codemcd.mydev.linkbucket.domain.LinkTag;
import com.codemcd.mydev.linkbucket.domain.LinkTagRepository;
import com.codemcd.mydev.linkbucket.domain.Tag;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LinkTagService {
    private final LinkTagRepository linkTagRepository;

    public LinkTagService(LinkTagRepository linkTagRepository) {
        this.linkTagRepository = linkTagRepository;
    }

    @Transactional
    public LinkTag save(Link link, Tag tag) {
        LinkTag linkTag = LinkTag.builder().link(link).tag(tag).build();

        return linkTagRepository.save(linkTag);
    }

    @Transactional(readOnly = true)
    public List<Tag> findTagsByLink(Link link) {
        return linkTagRepository.findTagsByLink(link);
    }
}
