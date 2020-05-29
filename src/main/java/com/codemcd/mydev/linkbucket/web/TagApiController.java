package com.codemcd.mydev.linkbucket.web;

import com.codemcd.mydev.linkbucket.service.TagService;
import com.codemcd.mydev.linkbucket.service.dto.TagResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TagApiController {
    private final TagService tagService;

    public TagApiController(TagService tagService) {
        this.tagService = tagService;
    }

    @GetMapping("/tags")
    public ResponseEntity<List<TagResponseDto>> findAll() {
        List<TagResponseDto> responses = tagService.findAll();

        return ResponseEntity.ok().body(responses);
    }
}
