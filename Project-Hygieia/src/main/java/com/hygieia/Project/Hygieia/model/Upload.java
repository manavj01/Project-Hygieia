package com.hygieia.Project.Hygieia.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "uploads")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Upload implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fileName;

    private String contentType;

    @Lob
    @Column(nullable = false)
    private byte[] data;


}
