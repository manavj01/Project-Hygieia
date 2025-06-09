package com.hygieia.Project.Hygieia.repository;

import com.hygieia.Project.Hygieia.model.Upload;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface UploadRepository extends JpaRepository<Upload, Long> {
    Upload findByDocumentId(Long documentId);
    byte[] findDocumentById(Long id);
}
