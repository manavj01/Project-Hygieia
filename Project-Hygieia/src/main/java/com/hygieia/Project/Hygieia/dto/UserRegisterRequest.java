package com.hygieia.Project.Hygieia.dto;

import com.hygieia.Project.Hygieia.model.Document;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class UserRegisterRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
