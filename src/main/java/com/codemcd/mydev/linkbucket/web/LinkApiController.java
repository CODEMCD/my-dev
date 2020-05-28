package com.codemcd.mydev.linkbucket.web;

import com.codemcd.mydev.linkbucket.service.LinkService;
import com.codemcd.mydev.linkbucket.service.dto.LinkRequestDto;
import com.codemcd.mydev.linkbucket.service.dto.LinkResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LinkApiController {
    private final LinkService linkService;

    public LinkApiController(LinkService linkService) {
        this.linkService = linkService;
    }

    @PostMapping("/links")
    public ResponseEntity<LinkResponseDto> save(@RequestBody LinkRequestDto linkRequestDto) {
        LinkResponseDto linkResponseDto = linkService.save(linkRequestDto);

        return ResponseEntity.ok().body(linkResponseDto);
    }

    @GetMapping("/links")
    public ResponseEntity<List<LinkResponseDto>> findAll() {
        List<LinkResponseDto> results = linkService.findAll();

        return ResponseEntity.ok().body(results);
    }
}
