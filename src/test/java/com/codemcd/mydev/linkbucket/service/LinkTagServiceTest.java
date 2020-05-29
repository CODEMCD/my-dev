package com.codemcd.mydev.linkbucket.service;

import com.codemcd.mydev.linkbucket.domain.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

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

    @AfterEach
    void tearDown() {
        linkRepository.deleteAll();
    }

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

    @Test
    @DisplayName("해당 Link가 가지고 있는 Tag를 가져온다.")
    void find_tags_by_link() {
        Link link = Link.builder()
                .url("www.github.com/codemcd")
                .title("my github")
                .description("This is...")
                .image("image").build();
        linkRepository.save(link);

        Tag tag1 = Tag.builder()
                .name("Java").build();
        Tag tag2 = Tag.builder()
                .name("Spring-Boot").build();
        Tag tag3 = Tag.builder()
                .name("JPA").build();
        tagRepository.save(tag1);
        tagRepository.save(tag2);
        tagRepository.save(tag3);

        linkTagService.save(link, tag1);
        linkTagService.save(link, tag2);
        linkTagService.save(link, tag3);

        List<Tag> tags = linkTagService.findTagsByLink(link);

        assertThat(tags.size()).isEqualTo(3);
        assertThat(tags).contains(tag1);
        assertThat(tags).contains(tag2);
        assertThat(tags).contains(tag3);
    }
}
