package com.codemcd.mydev.linkbucket.web;

import com.codemcd.mydev.linkbucket.service.TagService;
import com.codemcd.mydev.linkbucket.service.dto.TagResponseDto;
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
    void find_all() throws Exception {
        List<TagResponseDto> tags = Arrays.asList(
                new TagResponseDto(1L, "Java", 10),
                new TagResponseDto(2L, "Spring Boot", 7),
                new TagResponseDto(3L, "JPA", 5),
                new TagResponseDto(4L, "C++", 1)
        );

        given(tagService.findAll()).willReturn(tags);

        ResultActions actions = mvc.perform(get("/tags")
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print());

        actions.andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }
}
