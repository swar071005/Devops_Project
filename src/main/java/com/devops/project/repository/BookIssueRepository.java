package com.devops.project.repository;

import com.devops.project.model.BookIssue;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BookIssueRepository extends JpaRepository<BookIssue, Long> {
    List<BookIssue> findByStatusOrderByIssueDateDesc(String status);
}
