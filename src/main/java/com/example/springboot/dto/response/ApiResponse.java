package com.example.springboot.dto.response;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse {
    private String msg;
    private String code;
    private Object data;
}