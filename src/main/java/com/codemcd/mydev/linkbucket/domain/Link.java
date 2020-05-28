package com.codemcd.mydev.linkbucket.domain;

import com.codemcd.mydev.common.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "LINK")
public class Link extends BaseEntity {

    @Column(name = "URL", nullable = false)
    private String url;

    @Column(name = "TITLE", nullable = false)
    private String title;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "IMAGE")
    private String image;

    @OneToMany(mappedBy = "link")
    private List<LinkTag> tags = new ArrayList<>();

    @Builder
    public Link(String url, String title, String description, String image) {
        this.url = url;
        this.title = title;
        this.description = description;
        this.image = image;
    }

    public void update(Link another) {
        this.url = another.url;
        this.title = another.title;
        this.description = another.description;
        this.image = another.image;
    }
}
