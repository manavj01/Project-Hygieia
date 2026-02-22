package com.hygieia.Project.Hygieia.service;

import com.hygieia.Project.Hygieia.dto.DocumentResponse;
import com.hygieia.Project.Hygieia.dto.DocumentUploadRequest;
import com.hygieia.Project.Hygieia.dto.DownloadedFileResponse;
import com.hygieia.Project.Hygieia.enums.DocumentCategory;
import com.hygieia.Project.Hygieia.model.Document;
import com.hygieia.Project.Hygieia.model.Upload;
import com.hygieia.Project.Hygieia.model.User;
import com.hygieia.Project.Hygieia.repository.DocumentRepository;
import com.hygieia.Project.Hygieia.repository.UploadRepository;
import com.hygieia.Project.Hygieia.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
public class DocumentServiceImpl implements DocumentService {

    @Autowired
    private DocumentRepository documentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UploadRepository uploadRepository;

    private User getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated()) {
            throw new RuntimeException("Unauthorized");
        }
        String email = auth.getName(); // this is your JWT subject
        User user = userRepository.findUserByEmail(email);
        if (user == null) throw new RuntimeException("User not found for token");
        return user;
    }

    public String uploadDocument(DocumentUploadRequest metadata, MultipartFile file) {
        try {
            User user = getCurrentUser();

            Document document = Document.builder()
                    .title(metadata.getTitle())
                    .description(metadata.getDescription())
                    .uploadedAt(LocalDateTime.now())
                    .documentCategory(metadata.getDocumentCategory())
                    .userId(user.getId())
                    .build();


            Upload upload = Upload.builder()
                    .fileName(file.getOriginalFilename())
                    .contentType(file.getContentType())
                    .data(file.getBytes())
                    .build();

            document.setUpload(upload);
            documentRepository.save(document); // save the document which also saves the upload due to CascadeType.ALL

            log.info("File uploaded successfully: " + file.getOriginalFilename());
            return "Document uploaded successfully";
        } catch (Exception e) {
            log.warn("Failed to upload file: " + e.getMessage());
            throw new RuntimeException("Failed to upload document file", e);
        }
    }

    @Transactional
    public List<DocumentResponse> getUserDocuments() {
        User user = getCurrentUser();
        List<Document> documents = documentRepository.findDocumentsByUserId(user.getId());

        return documents.stream().map(
                document -> DocumentResponse.builder()
                        .title(document.getTitle())
                        .description(document.getDescription())
                        .uploadedAt(document.getUploadedAt())
                        .documentCategory(document.getDocumentCategory())
                        .build()
        ).toList();
    }

    @Transactional
    public boolean deleteDocument(Long documentId) {
        User user = getCurrentUser();

        Document document = documentRepository.findDocumentById(documentId);
        if (document == null) return false;
        if (!user.getId().equals(document.getUserId())) throw new RuntimeException("Forbidden");
        int row = documentRepository.deleteDocumentById(documentId);

        return row == 1;
    }

    @Transactional
    public DownloadedFileResponse downloadUserDocumentById(Long documentId) {
        User user = getCurrentUser();

        Document document = documentRepository.findDocumentById(documentId);
        if (document == null) throw new RuntimeException("Document not found");

        if (!user.getId().equals(document.getUserId())) {
            throw new RuntimeException("Forbidden");
        }

        Upload upload = document.getUpload();
        if (upload == null) throw new RuntimeException("No file for this document");

        byte[] data = upload.getData();

        return DownloadedFileResponse.builder()
                .fileName(upload.getFileName())
                .contentType(upload.getContentType())
                .data(data)
                .build();
    }

    @Transactional
    public List<DocumentResponse> getUserDocumentsByCategory(DocumentCategory documentCategory) {
        User user = getCurrentUser();
        List<Document> documents = documentRepository.findDocumentsByUserIdAndDocumentCategory(user.getId(), documentCategory);

        return documents.stream().map(
                document -> DocumentResponse.builder()
                        .title(document.getTitle())
                        .description(document.getDescription())
                        .uploadedAt(document.getUploadedAt())
                        .documentCategory(document.getDocumentCategory())
                        .build()
        ).toList();
    }

}
