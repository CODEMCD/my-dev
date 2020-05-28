package com.codemcd.mydev.linkbucket.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LinkTagRepository extends JpaRepository<LinkTag, Long> {
}
