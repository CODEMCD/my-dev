package com.codemcd.mydev.linkbucket.service.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Getter
public class LinkResponseDto {
    private Long id;
    private String url;
    private String title;
    private String description;
    private String image;
    private List<String> tags;
}
