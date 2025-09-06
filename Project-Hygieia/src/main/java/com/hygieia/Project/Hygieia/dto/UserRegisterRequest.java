package com.hygieia.Project.Hygieia.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserRegisterRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
