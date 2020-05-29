package com.codemcd.mydev.linkbucket.service.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Getter
public class TagResponseDto {
    private Long id;
    private String name;
    private int numOfLink;
}
