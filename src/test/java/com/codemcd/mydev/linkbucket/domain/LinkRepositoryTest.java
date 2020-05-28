package com.codemcd.mydev.linkbucket.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@DataJpaTest
public class LinkRepositoryTest {

    @Autowired
    private LinkRepository linkRepository;

    @Test
    @DisplayName("링크 엔티티 CRUD 작업이 데이터베이스에서 정상적으로 동작하는지 확인한다.")
    void crud() {
        // Create
        Link link = Link.builder()
                .url("www.github.com/codemcd")
                .title("my github")
                .description("This is...")
                .image("image").build();

        Link savedLink = linkRepository.save(link);

        assertThat(savedLink).isEqualTo(link);

        // Read
        Link foundLink = linkRepository.findById(link.getId()).orElseThrow(IllegalArgumentException::new);

        assertThat(foundLink).isEqualTo(link);

        // Update
        Link anotherLink = Link.builder()
                .url("updatedURL")
                .title("updatedTitle")
                .description("Updated...")
                .image("updatedImage").build();

        link.update(anotherLink);

        Link updatedLink = linkRepository.findById(link.getId()).orElseThrow(IllegalArgumentException::new);

        assertThat(updatedLink.getUrl()).isEqualTo(anotherLink.getUrl());
        assertThat(updatedLink.getTitle()).isEqualTo(anotherLink.getTitle());
        assertThat(updatedLink.getDescription()).isEqualTo(anotherLink.getDescription());
        assertThat(updatedLink.getImage()).isEqualTo(anotherLink.getImage());

        // Delete
        linkRepository.delete(link);

        assertThat(linkRepository.findById(link.getId())).isEmpty();
    }
}
