package com.hygieia.Project.Hygieia.service;

import com.hygieia.Project.Hygieia.model.Document;
import com.hygieia.Project.Hygieia.repository.DocumentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
//@RequiredArgsConstructor
public class DocumentService {

    @Autowired
    private DocumentRepository documentRepository;

    public Document uploadDocument(Document document) {
        return documentRepository.save(document);
    }

    public List<Document> getDocumentsByUserId(Long userId) {
        return documentRepository.findByUserId(userId);
    }

    public void deleteDocument(Long id) {
        documentRepository.deleteById(id);
    }
}
