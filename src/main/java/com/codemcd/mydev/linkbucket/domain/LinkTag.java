package com.codemcd.mydev.linkbucket.domain;

import com.codemcd.mydev.common.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "LINK_TAG")
public class LinkTag extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "LINK_ID")
    private Link link;

    @ManyToOne
    @JoinColumn(name = "TAG_ID")
    private Tag tag;
}
