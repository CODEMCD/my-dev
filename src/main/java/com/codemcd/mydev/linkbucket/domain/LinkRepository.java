package com.codemcd.mydev.linkbucket.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LinkRepository extends JpaRepository<Link, Long> {

    @Query("SELECT DISTINCT l FROM Link l JOIN FETCH l.tags")
    List<Link> findAllJoinFetch();
}
