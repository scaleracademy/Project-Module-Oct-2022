package com.scaler.blogapp.common;

import lombok.Data;

@Data
public class ErrorResponseDto {
    private String code;
    private String message;
}
