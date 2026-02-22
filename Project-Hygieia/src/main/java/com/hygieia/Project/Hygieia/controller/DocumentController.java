package com.hygieia.Project.Hygieia.controller;

import com.hygieia.Project.Hygieia.dto.DocumentUploadRequest;
import com.hygieia.Project.Hygieia.dto.DownloadedFileResponse;
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


    @DeleteMapping("/documents/{id}")
    public ResponseEntity<?> deleteDocument(@PathVariable Long id) throws Exception {
        boolean isDeleted = documentService.deleteDocument(id);
        if (isDeleted) {
            return ResponseEntity.ok("Document deleted successfully");
        } else {
            return ResponseEntity.status(404).body("Document not found");
        }
    }

    @PostMapping(value = "/users/documents", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> uploadDocument(@RequestParam String title,
                                            @RequestParam String description,
                                            @RequestParam DocumentCategory documentCategory,
                                            @RequestPart("file") MultipartFile file) {
        DocumentUploadRequest metadata = DocumentUploadRequest.builder()
                .title(title)
                .description(description)
                .documentCategory(documentCategory)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(documentService.uploadDocument(metadata, file));
    }

    @GetMapping("/documents/{documentId}/download")
    public ResponseEntity<?> downloadDocument(@PathVariable Long documentId) {
        DownloadedFileResponse file = documentService.downloadUserDocumentById(documentId);
        if (file == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Document not found");
        }
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFileName() + "\"")
                .contentType(MediaType.parseMediaType(file.getContentType()))
                .body(file.getData());
    }

    @GetMapping("/documents")
    public ResponseEntity<?> getDocumentsByCategory(@RequestParam(required = false) DocumentCategory documentCategory) {
        if (documentCategory == null){
            return ResponseEntity.ok(documentService.getUserDocuments());
        }
        return ResponseEntity.ok(documentService.getUserDocumentsByCategory(documentCategory));
    }

}