package com.codemcd.mydev.linkbucket.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {
    Optional<Tag> findByName(String tagName);

    @Query("SELECT DISTINCT t FROM Tag t JOIN FETCH t.links")
    List<Tag> findAllJoinFetch();
}
