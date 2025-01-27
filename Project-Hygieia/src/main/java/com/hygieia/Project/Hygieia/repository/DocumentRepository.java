package com.hygieia.Project.Hygieia.repository;

import com.hygieia.Project.Hygieia.model.Document;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DocumentRepository extends JpaRepository<Document, Long> {
    List<Document> findByUserId(Long userId);
}
