package com.hygieia.Project.Hygieia.dto;

import com.hygieia.Project.Hygieia.enums.DocumentCategory;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DocumentUploadRequest {
    private String title;
    private String description;
    private DocumentCategory documentCategory; // Assuming this is a string representation of the DocumentType enum
}
