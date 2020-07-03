package com.codemcd.mydev.linkbucket.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "LINK_TAG")
public class LinkTag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "LINK_ID")
    private Link link;

    @ManyToOne
    @JoinColumn(name = "TAG_ID")
    private Tag tag;

    @Builder
    public LinkTag(Link link, Tag tag) {
        this.link = link;
        this.tag = tag;
    }

    public void update(LinkTag another) {
        this.link = another.link;
        this.tag = another.tag;
    }
}
