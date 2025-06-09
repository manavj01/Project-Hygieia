package com.hygieia.Project.Hygieia.service;

import com.hygieia.Project.Hygieia.dto.DocumentUploadRequest;
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
public class DocumentService {

    @Autowired
    private DocumentRepository documentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UploadRepository uploadRepository;

    public String  uploadDocument(DocumentUploadRequest metadata, MultipartFile file) {
        User user = userRepository.findById(metadata.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Document document = Document.builder()
                .title(metadata.getTitle())
                .description(metadata.getDescription())
                .uploadedAt(LocalDateTime.now())
                .documentCategory(metadata.getDocumentCategory())
                .userId(user.getId())
                .build();

        Document savedDocument = documentRepository.save(document);

        try {
            Upload upload = Upload.builder()
                    .fileName(file.getOriginalFilename())
                    .contentType(file.getContentType())
                    .data(file.getBytes())
                    .document(savedDocument)
                    .build();


            uploadRepository.save(upload);

            log.info("File uploaded successfully: " + file.getOriginalFilename());
            return "Document uploaded successfully";
        } catch (Exception e) {
            log.warn("Failed to upload file: " + e.getMessage());
            throw new RuntimeException("Failed to upload document file", e);
        }

    }

    public List<Document> getDocumentsByUserId(Long userId) {
        return documentRepository.findByUserId(userId);
    }

    public Boolean deleteDocument(Long id) {
       Boolean isDeleted = documentRepository.deleteDocumentById(id);
        if (isDeleted) {
            return true;
        } else {
            return false;
        }
    }

    @Transactional
    public Upload getDocumentById(Long documentId) {
        try {

            Upload upload = uploadRepository.findByDocumentId(documentId);
//            if (upload == null ) {
//                throw new RuntimeException("No document found with the given ID");
//            }
            return upload;
        } catch (Exception e) {
            log.error("Error retrieving document: " + e.getMessage());
            throw new RuntimeException("Failed to retrieve document", e);
        }
    }
}
