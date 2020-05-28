package com.codemcd.mydev.linkbucket.domain;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@DataJpaTest
public class LinkTagRepositoryTest {

    @Autowired
    private LinkTagRepository linkTagRepository;

    @Test
    void crud() {
        // Create
        Link link = Link.builder()
                .url("www.github.com/codemcd")
                .title("my github")
                .description("This is...")
                .image("image").build();
        Tag tag = Tag.builder()
                .name("Java").build();
        LinkTag linkTag = LinkTag.builder()
                .link(link)
                .tag(tag).build();

        LinkTag savedLinkTag = linkTagRepository.save(linkTag);

        assertThat(savedLinkTag).isEqualTo(linkTag);

        // Read
        LinkTag foundLinkTag = linkTagRepository.findById(linkTag.getId())
                .orElseThrow(IllegalArgumentException::new);

        assertThat(foundLinkTag).isEqualTo(linkTag);

        // Update
        Tag anotherTag = Tag.builder()
                .name("Kotlin").build();

        LinkTag anotherLinkTag = LinkTag.builder()
                .link(link)
                .tag(anotherTag).build();

        linkTag.update(anotherLinkTag);

        LinkTag updatedLinkTag = linkTagRepository.findById(linkTag.getId())
                .orElseThrow(IllegalArgumentException::new);

        assertThat(updatedLinkTag.getLink()).isEqualTo(anotherLinkTag.getLink());
        assertThat(updatedLinkTag.getTag().getName()).isEqualTo(anotherLinkTag.getTag().getName());

        // Delete
        linkTagRepository.delete(linkTag);

        assertThat(linkTagRepository.findById(linkTag.getId())).isEmpty();
    }
}
