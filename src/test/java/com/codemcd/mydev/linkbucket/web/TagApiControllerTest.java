package com.codemcd.mydev.linkbucket.web;

import com.codemcd.mydev.linkbucket.service.TagService;
import com.codemcd.mydev.linkbucket.service.dto.LinkResponseDto;
import com.codemcd.mydev.linkbucket.service.dto.TagResponseDto;
import com.codemcd.mydev.linkbucket.service.exception.NotFoundTagNameException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@WebMvcTest(controllers = TagApiController.class)
public class TagApiControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private TagService tagService;

    @Test
    @DisplayName("Tag 전체 조회")
    void find_all() throws Exception {
        List<TagResponseDto> responses = Arrays.asList(
                new TagResponseDto(1L, "Java", 10),
                new TagResponseDto(2L, "Spring-Boot", 7),
                new TagResponseDto(3L, "JPA", 5),
                new TagResponseDto(4L, "C++", 1)
        );

        given(tagService.findAll()).willReturn(responses);

        ResultActions actions = mvc.perform(get("/tags")
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print());

        actions.andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    @DisplayName("특정 Tag 조회")
    void find() throws Exception {
        String url1 = "www.github.com/codemcd";
        String title1 = "my github";
        String description1 = "This is...";
        String image1 = "image file 1";
        List<String> tags1 = Arrays.asList("Java", "Spring-Boot", "JPA");

        String url2 = "www.github.com/park";
        String title2 = "another github";
        String description2 = "This is...";
        String image2 = "image file 2";
        List<String> tags2 = Arrays.asList("Kotlin", "Spring-Boot", "JPA", "MySQL");

        List<LinkResponseDto> responses = Arrays.asList(
                new LinkResponseDto(1L, url1, title1, description1, image1, tags1),
                new LinkResponseDto(2L, url2, title2, description2, image2, tags2)
        );

        given(tagService.find(any())).willReturn(responses);

        ResultActions actions = mvc.perform(get("/tags/Spring-Boot")
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print());

        actions.andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    @DisplayName("존재하지 않는 Tag Name으로 요청했을 때 Bad Request 상태의 응답을 받는다.")
    void invalid_tag_name_request() throws Exception {
        given(tagService.find(any())).willThrow(NotFoundTagNameException.class);

        ResultActions actions = mvc.perform(get("/tags/non-tag")
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print());

        actions.andExpect(status().isBadRequest());
    }
}
