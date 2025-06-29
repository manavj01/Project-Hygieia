package com.hygieia.Project.Hygieia.repository;

import com.hygieia.Project.Hygieia.enums.DocumentCategory;
import com.hygieia.Project.Hygieia.model.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {

    List<Document> findDocumentsByUserId(Long userId);

    Boolean deleteDocumentById(Long id);

    Document findDocumentById(Long id);

    List<Document> findDocumentsByUserIdAndDocumentCategory(Long userId, DocumentCategory documentCategory);
}
