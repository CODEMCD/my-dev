package com.codemcd.mydev.linkbucket.service;

import com.codemcd.mydev.linkbucket.service.dto.LinkRequestDto;
import com.codemcd.mydev.linkbucket.service.dto.LinkResponseDto;
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

    @Test
    @DisplayName("LinkRequestDto로 Link 저장 요청이 오면, 데이터베이스에 저장 후 LinkResponseDto를 반환한다.")
    void save() {
        String url = "www.github.com/codemcd";
        String title = "my github";
        String description = "This is...";
        String image = "image file";
        List<String> tags = Arrays.asList("Java", "Spring Boot", "JPA");

        LinkRequestDto linkRequestDto = new LinkRequestDto(url, title, description, image, tags);

        LinkResponseDto linkResponseDto = linkService.save(linkRequestDto);

        assertThat(linkResponseDto.getId()).isNotNull();
    }
}
