package com.spring.codeblog.repository;

import com.spring.codeblog.model.Work;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkblogRepository extends JpaRepository<Work, Long> {
}
