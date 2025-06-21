package com.hygieia.Project.Hygieia.service;


import com.hygieia.Project.Hygieia.dto.DocumentResponse;
import com.hygieia.Project.Hygieia.dto.DocumentUploadRequest;
import com.hygieia.Project.Hygieia.enums.DocumentCategory;
import com.hygieia.Project.Hygieia.model.Document;
import com.hygieia.Project.Hygieia.model.Upload;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface DocumentService {

    List<DocumentResponse> getDocumentsByUserId(Long userId);

    boolean deleteDocument(Long id);

    String uploadDocument(DocumentUploadRequest metadata, MultipartFile file);

    Upload downloadDocumentById(Long documentId);

    List<DocumentResponse> getDocumentsByCategory(DocumentCategory documentCategory, Long userId);
}
