package com.hygieia.Project.Hygieia.model;


import com.hygieia.Project.Hygieia.enums.DocumentCategory;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "documents")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Document implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    private String description;

    private LocalDateTime uploadedAt;

    @Column(name = "user_id")
    private Long userId; // THis is kept to make querying easier.

    @Column(name = "document_category", nullable = false)
    @Enumerated(EnumType.STRING)
    private DocumentCategory documentCategory;

    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "upload_id", unique = true)
    private Upload upload;
}
