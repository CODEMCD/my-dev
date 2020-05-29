package com.codemcd.mydev.linkbucket.service;

import com.codemcd.mydev.linkbucket.service.dto.LinkRequestDto;
import com.codemcd.mydev.linkbucket.service.dto.LinkResponseDto;
import org.junit.jupiter.api.AfterEach;
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
public class LinkServiceTest {

    @Autowired
    private LinkService linkService;

    @AfterEach
    void tearDown() {
        linkService.deleteAll();
    }

    @Test
    @DisplayName("LinkRequestDto로 Link 저장 요청이 오면, 데이터베이스에 저장 후 LinkResponseDto를 반환한다.")
    void save() {
        String url = "www.github.com/codemcd";
        String title = "my github";
        String description = "This is...";
        String image = "image file";
        List<String> tags = Arrays.asList("Java", "Spring-Boot", "JPA");

        LinkRequestDto linkRequestDto = new LinkRequestDto(url, title, description, image, tags);

        LinkResponseDto linkResponseDto = linkService.save(linkRequestDto);

        assertThat(linkResponseDto.getId()).isNotNull();
    }

    @Test
    @DisplayName("데이터베이스에 저장되어 있는 전체 Link 리스트를 반환한다.")
    void find_all() {
        String url1 = "www.github.com/codemcd";
        String title1 = "my github";
        String description1 = "This is...";
        String image1 = "image file 1";
        List<String> tags1 = Arrays.asList("Java", "Spring-Boot", "JPA");

        LinkRequestDto linkRequestDto1 = new LinkRequestDto(url1, title1, description1, image1, tags1);
        linkService.save(linkRequestDto1);

        String url2 = "www.github.com/park";
        String title2 = "another github";
        String description2 = "This is...";
        String image2 = "image file 2";
        List<String> tags2 = Arrays.asList("Kotlin", "Spring-Boot", "JPA", "MySQL");

        LinkRequestDto linkRequestDto2 = new LinkRequestDto(url2, title2, description2, image2, tags2);
        linkService.save(linkRequestDto2);

        List<LinkResponseDto> responses = linkService.findAll();

        assertThat(responses).hasSize(2);
    }
}
