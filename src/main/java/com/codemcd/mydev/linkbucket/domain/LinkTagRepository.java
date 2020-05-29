package com.codemcd.mydev.linkbucket.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LinkTagRepository extends JpaRepository<LinkTag, Long> {

    @Query("SELECT lt.tag FROM LinkTag lt WHERE lt.link = ?1")
    List<Tag> findTagsByLink(Link link);
}
