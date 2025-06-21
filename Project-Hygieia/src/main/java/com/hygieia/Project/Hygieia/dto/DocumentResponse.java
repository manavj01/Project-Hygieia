package com.hygieia.Project.Hygieia.dto;

import com.hygieia.Project.Hygieia.enums.DocumentCategory;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class DocumentResponse {
    private String title;

    private String description;

    private LocalDateTime uploadedAt;

    private DocumentCategory documentCategory;
}
