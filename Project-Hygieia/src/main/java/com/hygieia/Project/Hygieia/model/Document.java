package com.hygieia.Project.Hygieia.model;


import com.hygieia.Project.Hygieia.enums.DocumentType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "documents")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String type;  // Graduation, Certificate, Offer Letter, etc.

    private String description;

    private LocalDateTime uploadDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "document_type", nullable = false)
    private DocumentType documentType; // Enum to specify the type of document (e.g., PDF, Word, etc.)
}
