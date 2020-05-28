package com.codemcd.mydev.linkbucket.web;

import com.codemcd.mydev.linkbucket.service.LinkService;
import com.codemcd.mydev.linkbucket.service.dto.LinkRequestDto;
import com.codemcd.mydev.linkbucket.service.dto.LinkResponseDto;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@WebMvcTest(controllers = LinkApiController.class)
public class LinkApiControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private LinkService linkService;

    @Test
    @DisplayName("정상적으로 Link 데이터를 저장 요청했을 때 저장 후 해당 Link의 id를 응답한다.")
    void save() throws Exception {
        String url = "www.github.com/codemcd";
        String title = "my github";
        String description = "This is...";
        String image = "image file";
        List<String> tags = Arrays.asList("Java", "Spring Boot", "JPA");

        LinkRequestDto linkRequestDto = new LinkRequestDto(url, title, description, image, tags);
        LinkResponseDto linkResponseDto = new LinkResponseDto(1L);

        given(linkService.save(any())).willReturn(linkResponseDto);

        ObjectMapper om = new ObjectMapper();
        String inputJson = om.writeValueAsString(linkRequestDto);

        ResultActions actions = mvc.perform(post("/links")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .content(inputJson))
                .andDo(print());

        actions.andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNotEmpty());
    }
}
