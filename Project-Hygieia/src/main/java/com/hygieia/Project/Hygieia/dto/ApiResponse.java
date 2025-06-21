package com.hygieia.Project.Hygieia.dto;

import lombok.Data;

@Data
public class ApiResponse {
    private boolean success;
    private String message;

    public ApiResponse(String message, boolean success) {
        this.message = message;
        this.success = success;
    }
}
