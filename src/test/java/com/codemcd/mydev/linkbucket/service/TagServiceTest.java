package com.codemcd.mydev.linkbucket.service;

import com.codemcd.mydev.linkbucket.domain.Tag;
import com.codemcd.mydev.linkbucket.service.dto.LinkRequestDto;
import com.codemcd.mydev.linkbucket.service.dto.TagResponseDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@SpringBootTest
public class TagServiceTest {

    @Autowired
    private TagService tagService;

    @Autowired
    private LinkService linkService;

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

    @Test
    @DisplayName("데이터베이스에 저장되어 있는 모든 Tag 정보를 가져온다.")
    void find_all() {
        String url1 = "www.github.com/codemcd";
        String title1 = "my github";
        String description1 = "This is...";
        String image1 = "image file 1";
        List<String> tags1 = Arrays.asList("Java", "Spring Boot", "JPA");

        LinkRequestDto linkRequestDto1 = new LinkRequestDto(url1, title1, description1, image1, tags1);
        linkService.save(linkRequestDto1);

        String url2 = "www.github.com/park";
        String title2 = "another github";
        String description2 = "This is...";
        String image2 = "image file 2";
        List<String> tags2 = Arrays.asList("Kotlin", "Spring Boot", "JPA", "MySQL");

        LinkRequestDto linkRequestDto2 = new LinkRequestDto(url2, title2, description2, image2, tags2);
        linkService.save(linkRequestDto2);

        List<TagResponseDto> responses = tagService.findAll();

        responses.forEach(this::printTagInfo);

        assertThat(responses.size()).isEqualTo(5);
    }

    private void printTagInfo(TagResponseDto res) {
        System.out.println("tag id: " + res.getId() + ", " +
                "tag name: " + res.getName() + ", " +
                "num of link: " + res.getNumOfLink());
    }
}
