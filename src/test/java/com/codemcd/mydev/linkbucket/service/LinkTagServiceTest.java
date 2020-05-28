package com.codemcd.mydev.linkbucket.service;

import com.codemcd.mydev.linkbucket.domain.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@SpringBootTest
public class LinkTagServiceTest {

    @Autowired
    private LinkTagService linkTagService;

    @Autowired
    private LinkRepository linkRepository;

    @Autowired
    private TagRepository tagRepository;

    @Test
    @DisplayName("Link, Tag의 정보를 갖는 LinkTag를 저장한다.")
    void save() {
        Link link = Link.builder()
                .url("www.github.com/codemcd")
                .title("my github")
                .description("This is...")
                .image("image").build();
        Tag tag = Tag.builder()
                .name("Java").build();
        linkRepository.save(link);
        tagRepository.save(tag);

        LinkTag linkTag = linkTagService.save(link, tag);

        assertThat(linkTag.getLink()).isEqualTo(link);
        assertThat(linkTag.getTag()).isEqualTo(tag);
    }
}
