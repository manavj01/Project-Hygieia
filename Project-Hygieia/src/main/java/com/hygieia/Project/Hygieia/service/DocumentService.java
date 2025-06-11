package com.hygieia.Project.Hygieia.service;


import com.hygieia.Project.Hygieia.dto.DocumentUploadRequest;
import com.hygieia.Project.Hygieia.model.Document;
import com.hygieia.Project.Hygieia.model.Upload;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface DocumentService {

    List<Document> getDocumentsByUserId(Long userId);

    boolean deleteDocument(Long id);

    String uploadDocument(DocumentUploadRequest metadata, MultipartFile file);

    Upload downloadDocumentById(Long documentId);
}
