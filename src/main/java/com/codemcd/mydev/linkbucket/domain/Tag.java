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
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "TAG")
public class Tag extends BaseEntity {

    @Column(name = "NAME", nullable = false)
    private String name;

    @OneToMany(mappedBy = "tag")
    private Set<LinkTag> links = new LinkedHashSet<>();

    @Builder
    public Tag(String name) {
        this.name = name;
    }

    public void update(Tag another) {
        this.name = another.name;
    }
}
