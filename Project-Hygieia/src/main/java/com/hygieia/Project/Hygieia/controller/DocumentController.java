package com.hygieia.Project.Hygieia.controller;

import com.hygieia.Project.Hygieia.dto.DocumentUploadRequest;
import com.hygieia.Project.Hygieia.enums.DocumentCategory;
import com.hygieia.Project.Hygieia.model.Document;
import com.hygieia.Project.Hygieia.model.Upload;
import com.hygieia.Project.Hygieia.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api")
public class DocumentController {

    @Autowired
    private DocumentService documentService;

    @GetMapping("/users/{userId}/documents")
    public ResponseEntity<?> getDocumentsByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(documentService.getDocumentsByUserId(userId));
    }

    @DeleteMapping("/documents/{id}")
    public ResponseEntity<?> deleteDocument(@PathVariable Long id) throws Exception {
        boolean isDeleted = documentService.deleteDocument(id);
        if (isDeleted) {
            return ResponseEntity.ok("Document deleted successfully");
        } else {
            return ResponseEntity.status(404).body("Document not found");
        }
    }

    @PostMapping(value = "/users/{userId}/documents", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> uploadDocument(@RequestParam String title,
                                            @RequestParam String description,
                                            @RequestParam DocumentCategory documentCategory,
                                            @RequestParam Long userId,
                                            @RequestPart("file") MultipartFile file) {
        DocumentUploadRequest metadata = DocumentUploadRequest.builder()
                .title(title)
                .description(description)
                .documentCategory(documentCategory)
                .userId(userId)
                .build();
        return ResponseEntity.ok(documentService.uploadDocument(metadata, file));
    }

    @GetMapping("/documents/{documentId}")
    public ResponseEntity<?> downloadDocumentById(@PathVariable Long documentId) {
        Upload upload = documentService.downloadDocumentById(documentId);
        if (upload == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Document not found");
        }
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + upload.getFileName() + "\"")
                .contentType(MediaType.parseMediaType(upload.getContentType()))
                .body(upload.getData());
    }

    @GetMapping("/documents/category")
    public ResponseEntity<?> getDocumentsByCategory(@RequestParam DocumentCategory documentCategory,
                                                    @RequestParam Long userId) {
        return ResponseEntity.status(HttpStatus.OK).body(documentService.getDocumentsByCategory(documentCategory, userId));
    }

}