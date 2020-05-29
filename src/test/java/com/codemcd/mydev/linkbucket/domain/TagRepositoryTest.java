package com.codemcd.mydev.linkbucket.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@DataJpaTest
public class TagRepositoryTest {

    @Autowired
    private TagRepository tagRepository;

    @Test
    @DisplayName("Tag 엔티티 CRUD 작업이 데이터베이스에서 정상적으로 동작하는지 확인한다.")
    void crud() {
        // Create
        Tag tag = Tag.builder()
                .name("Java").build();

        Tag savedTag = tagRepository.save(tag);

        assertThat(savedTag).isEqualTo(tag);

        // Read
        Tag foundTag = tagRepository.findById(tag.getId()).orElseThrow(IllegalArgumentException::new);

        assertThat(foundTag).isEqualTo(tag);

        // Update
        Tag anotherTag = Tag.builder()
                .name("Spring-Boot").build();

        tag.update(anotherTag);

        Tag updatedTag = tagRepository.findById(tag.getId()).orElseThrow(IllegalArgumentException::new);

        assertThat(updatedTag.getName()).isEqualTo(anotherTag.getName());

        // Delete
        tagRepository.delete(tag);

        assertThat(tagRepository.findById(tag.getId())).isEmpty();
    }

    @Test
    void find_by_name() {
        String tagName = "Java";
        Tag tag = Tag.builder().name(tagName).build();

        tagRepository.save(tag);

        Tag foundTag = tagRepository.findByName(tagName).orElseThrow(IllegalArgumentException::new);

        assertThat(foundTag).isEqualTo(tag);
    }
}
