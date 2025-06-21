package com.hygieia.Project.Hygieia.service;

import com.hygieia.Project.Hygieia.dto.DocumentResponse;
import com.hygieia.Project.Hygieia.dto.DocumentUploadRequest;
import com.hygieia.Project.Hygieia.enums.DocumentCategory;
import com.hygieia.Project.Hygieia.model.Document;
import com.hygieia.Project.Hygieia.model.Upload;
import com.hygieia.Project.Hygieia.model.User;
import com.hygieia.Project.Hygieia.repository.DocumentRepository;
import com.hygieia.Project.Hygieia.repository.UploadRepository;
import com.hygieia.Project.Hygieia.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
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

    public String uploadDocument(DocumentUploadRequest metadata, MultipartFile file) {
        try {
            User user = userRepository.findById(metadata.getUserId())
                    .orElseThrow(() -> new RuntimeException("User not found"));

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
    public List<DocumentResponse> getDocumentsByUserId(Long userId) {
        List<Document> documents = documentRepository.findDocumentsByUserId(userId);
        List<DocumentResponse> documentResponsesDTO = documents.stream().map(
                document -> DocumentResponse.builder()
                        .title(document.getTitle())
                        .description(document.getDescription())
                        .uploadedAt(document.getUploadedAt())
                        .documentCategory(document.getDocumentCategory())
                        .build()
        ).toList();
        DocumentResponse.builder().build();

        return documentResponsesDTO;
    }

    public boolean deleteDocument(Long id) {
        boolean isDeleted = documentRepository.deleteDocumentById(id);
        if (isDeleted) {
            return true;
        } else {
            return false;
        }
    }

    @Transactional
    public Upload downloadDocumentById(Long documentId) {
        try {
            Document document = documentRepository.findDocumentById(documentId);
            Upload upload = document.getUpload();
            if (upload == null) {
                throw new RuntimeException("No document found with the given ID");
            }
            return upload;
        } catch (Exception e) {
            log.error("Error retrieving document: " + e.getMessage());
            throw new RuntimeException("Failed to retrieve document", e);
        }
    }

    @Transactional
    public List<DocumentResponse> getDocumentsByCategory(DocumentCategory documentCategory, Long userId) {
        List<Document> documents = documentRepository.findDocumentsByUserIdAndDocumentCategory(userId, documentCategory);
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
