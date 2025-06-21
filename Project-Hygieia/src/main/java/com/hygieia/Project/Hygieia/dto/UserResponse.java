package com.hygieia.Project.Hygieia.dto;

import com.hygieia.Project.Hygieia.model.Document;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class UserResponse {
    Long id;
    String firstName;
    String lastName;
    String email;
    List<DocumentResponse> documents;
}