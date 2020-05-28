package com.codemcd.mydev.linkbucket.service;

import com.codemcd.mydev.linkbucket.domain.Tag;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@SpringBootTest
public class TagServiceTest {

    @Autowired
    private TagService tagService;

    @Test
    @DisplayName("데이터베이스에 없는 Tag는 저장한다.")
    void save() {
        String tagName = "Java";

        Tag savedTag = tagService.saveOrGet(tagName);

        assertThat(savedTag.getName()).isEqualTo(tagName);
    }

    @Test
    @DisplayName("이미 데이터베이스에 존재하는 Tag는 생성하지 않고 가져온다.")
    void get() {
        String tagName = "Java";
        Tag savedTag = tagService.saveOrGet(tagName);

        Tag gotTag = tagService.saveOrGet(tagName);

        assertThat(gotTag).isEqualTo(savedTag);
    }
}
