package com.hygieia.Project.Hygieia.dto;

import lombok.Data;

@Data
public class UserLoginRequest {
    private String email;
    private String password;
}